// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    if (System.getenv("EXCLUDES_SAMPLES") != "true") {
        alias(libs.plugins.comGithub7hensEzgradle)
    }
}

allprojects {
    group = "com.github.7hens.ezgradle"
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
    }
}
