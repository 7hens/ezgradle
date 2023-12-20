// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    `kotlin-dsl` apply false
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.2.0" apply false
    if (System.getenv("EXCLUDES_SAMPLES") != "true") {
        id("com.github.7hens.ezgradle") version "-SNAPSHOT"
    }
}

allprojects {
    group = "com.github.7hens.ezgradle"
    version = properties["VERSION"] ?: "-SNAPSHOT"
    logger.info("Project {}, {}", name, version)
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
    }
}
