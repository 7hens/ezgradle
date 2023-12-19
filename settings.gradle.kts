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

if (System.getenv("EXCLUDES_SAMPLES") != "true") {
    include(":sample-android-app")
    include(":sample-android-lib")
    include(":sample-java-lib")
}