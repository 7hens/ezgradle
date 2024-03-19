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
    val moduleText = System.getenv("MODULES") ?: props.getProperty("modules") ?: ""
    val modules = moduleText.split(",").map { it.trim() }.toSet()
    val isPublish = gradle.startParameter.taskNames.any { it.contains(Regex("[Pp]ublish")) }
    val containsAll = isPublish || modules.contains("*")
    (rootDir.listFiles() ?: emptyArray())
        .filter { File(it, "build.gradle.kts").exists() }
        .map { it.name }
        .filter { it.startsWith("sample-") && (modules.contains(it) || containsAll) }
        .forEach { include(":$it") }
}
