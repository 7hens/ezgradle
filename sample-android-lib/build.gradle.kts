plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
    id("me.thens.ezgradle")
}

android {
    namespace = "com.example.mylibrary"
}

dependencies {
    builtInLibs {
        test()
    }
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
}