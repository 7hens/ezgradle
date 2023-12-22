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

fun getProperty(name: String): String? {
    return if (hasProperty(name)) properties[name].toString() else null
}

val projectVersion = getProperty("version") ?: "-SNAPSHOT"
println("#VERSION: $projectVersion")

allprojects {
    group = "com.github.7hens.ezgradle"
    version = projectVersion
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
    }
}
