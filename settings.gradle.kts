pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ezgradle"
includeBuild("ezgradle-plugin")
include(":sample-android-app")
include(":sample-android-lib")
include(":sample-java-lib")
