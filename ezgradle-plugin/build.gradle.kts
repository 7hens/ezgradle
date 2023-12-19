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

gradlePlugin {
    plugins {
        fun register(pluginId: String, className: String) {
            register(pluginId) {
                id = pluginId
                implementationClass = className
            }
        }
        register("com.github.7hens.ezgradle", "me.thens.ezgradle.EzGradlePlugin")
    }
}

dependencies {
    implementation(platform(project(":ezgradle-bom")))
    fun plugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"
    fun plugin(id: String) = "$id:$id.gradle.plugin"
    fun kotlin(name: String) = "org.jetbrains.kotlin.$name"

    val agpVersion = "8.2.0"
    implementation(plugin("com.android.application"))
    implementation(plugin("com.android.library"))
    val kotlinVersion = "1.8.10"
    implementation(plugin(kotlin("jvm")))
    implementation(plugin(kotlin("android")))
    implementation(plugin(kotlin("plugin.serialization")))
    implementation(plugin(kotlin("plugin.parcelize")))
    implementation(plugin("com.google.dagger.hilt.android"))
//    implementation(kotlin("gradle-plugin"))
    implementation(gradleApi())
    implementation(localGroovy())
}

fun Project.generateBuildConfig(packageName: String) {
    val className = "ProjectBuildConfig"
    val packageDir = packageName.replace('.', '/')
    val outputDir = layout.buildDirectory.file("generated/src/main/kotlin/").get().asFile
    val outputFile = File(outputDir, "$packageDir/$className.kt")
    outputFile.parentFile?.mkdirs()
    val props = mutableMapOf<String, Any?>().apply {
        putAll(properties)
        put("GROUP", project.group)
        put("NAME", project.name)
        put("VERSION", project.version)
    }
    val fields = props.entries
        .filter { it.key.matches(Regex("[0-9A-Z_]+")) }
        .distinctBy { it.key }
        .joinToString("\n   ") { "val ${it.key} = \"${it.value}\"" }
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

