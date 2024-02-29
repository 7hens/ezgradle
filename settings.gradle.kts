import java.util.Properties

pluginManagement {
//    includeBuild("ezgradle-plugin")
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}

rootProject.name = "ezgradle"
include(":ezgradle-bom")
include(":ezgradle-plugin")
project(":ezgradle-plugin").name = "com.github.7hens.ezgradle.gradle.plugin"

val props = Properties().apply { file("local.properties").reader().use { load(it) } }
if (System.getenv("EXCLUDES_SAMPLES") != "true") {
    include(":sample-android-app")
    val modules = props.getProperty("project.activeModules", "").split(",").map { it.trim() }
    val isPublish = gradle.startParameter.taskNames.any { it.contains(Regex("[Pp]ublish")) }
    val containsAll = isPublish || modules.contains("*")
    listOf("sample-android-lib", "sample-java-lib")
        .filter { modules.contains(it) || containsAll }
        .forEach { include(":$it") }
}
