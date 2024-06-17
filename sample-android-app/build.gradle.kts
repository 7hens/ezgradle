plugins {
    id("com.android.application")
    kotlin("android")
    id("com.gitlab.7hens.ezgradle.project")
}

android {
    buildFeatures {
        compose = true
    }
}

dependencies {
    builtInLibs {
        compose()
        test()
    }
    implementation(localProject(":sample-java-lib"))
    implementation(localProject(":sample-android-lib"))
}