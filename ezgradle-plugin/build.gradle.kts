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

