import java.util.Properties

plugins {
    `kotlin-dsl`
    id("java-gradle-plugin")
    id("maven-publish")
}

sourceSets {
    get("main").apply {
        kotlin.srcDirs("build/generated/src/main/kotlin")
    }
}

publishing {
    publications {
        create<MavenPublication>("pluginMaven") {
            version = project.version.toString()
            from(components.getByName("java"))
        }
    }
}

gradlePlugin {
    isAutomatedPublishing = false
    plugins {
        register("ezgradle") {
            id = "com.github.7hens.ezgradle"
            implementationClass = "me.thens.ezgradle.EzGradlePlugin"
        }
    }
}

dependencies {
    fun plugin(id: String) = "$id:$id.gradle.plugin"
    fun kotlin(name: String) = "org.jetbrains.kotlin.$name"

    implementation(platform(project(":ezgradle-bom")))
    implementation(plugin("com.android.application"))
    implementation(plugin("com.android.library"))
    implementation(plugin(kotlin("jvm")))
    implementation(plugin(kotlin("android")))
    implementation(plugin(kotlin("plugin.serialization")))
    implementation(plugin(kotlin("plugin.parcelize")))
    implementation(plugin("com.google.dagger.hilt.android"))
    implementation(gradleApi())
    implementation(localGroovy())
}

private fun Project.getConfigProps(): Map<Any, Any> {
    val gradleKeys = Properties().run {
        rootProject.file("gradle.properties").takeIf { it.isFile }?.inputStream()?.use { load(it) }
        keys
    }
    val myProps = Properties().apply {
        properties.forEach { (k, v) -> k.takeIf { it in gradleKeys }?.let { put(k, v ?: "") } }
        rootProject.file("local.properties").takeIf { it.isFile }?.inputStream()?.use { load(it) }
    }
    return mutableMapOf<Any, Any>().apply {
        myProps.forEach { (key, value) ->
            key.toString().takeIf { it.matches(Regex("[A-Z_]+")) }?.let { put(it, value ?: "") }
        }
        put("GROUP", project.group)
        put("NAME", project.name)
        put("VERSION", project.version)
    }
}

fun Project.generateBuildConfig(packageName: String) {
    val className = "BuildConfig"
    val packageDir = packageName.replace('.', '/')
    val outputDir = layout.buildDirectory.file("generated/src/main/kotlin/").get().asFile
    val outputFile = File(outputDir, "$packageDir/$className.kt")
    outputFile.parentFile?.mkdirs()
    val fields =
        getConfigProps().entries.joinToString("\n   ") { "val ${it.key} = \"${it.value}\"" }
    outputFile.writeText(
        """
        |package $packageName
        |
        |object $className {
        |   $fields
        |}
        """.trimMargin("|")
    )
}

afterEvaluate {
    tasks.getByName("compileKotlin") {
        doFirst {
            generateBuildConfig("me.thens.ezgradle")
        }
    }
//    tasks.getByName("publishToMavenLocal").apply {
//        dependsOn(tasks.getByName("assemble"))
//    }
}

