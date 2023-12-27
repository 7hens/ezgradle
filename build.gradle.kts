// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    val kotlinVersion = System.getProperty("KOTLIN_VERSION")
    val agpVersion = System.getProperty("AGP_VERSION")
    if (System.getenv("EXCLUDES_SAMPLES") != "true") {
        id("com.github.7hens.ezgradle") version "-SNAPSHOT"
        id("org.jetbrains.kotlin.android") version kotlinVersion apply false
        id("com.android.application") version agpVersion apply false
        id("com.android.library") version agpVersion apply false
    }
}

fun getProperty(name: String): String? {
    return if (hasProperty(name)) properties[name].toString() else null
}

val projectVersion = getProperty("VERSION") ?: "-SNAPSHOT"
println("#VERSION: $projectVersion")

allprojects {
    group = "com.github.7hens.ezgradle"
    version = projectVersion
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
    }
}
