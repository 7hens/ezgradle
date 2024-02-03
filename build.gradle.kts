// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    if (System.getenv("EXCLUDES_SAMPLES") != "true") {
        id("com.github.7hens.ezgradle") version "-SNAPSHOT"
    }
}

allprojects {
    group = "com.github.7hens.ezgradle"
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
    }
}
