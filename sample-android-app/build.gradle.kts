plugins {
    id("com.android.application")
    kotlin("android")
    id("com.github.7hens.ezgradle")
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
    implementation("androidx.core:core-ktx")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android")
    implementation(localProject(":sample-java-lib"))
    implementation(localProject(":sample-android-lib"))
}