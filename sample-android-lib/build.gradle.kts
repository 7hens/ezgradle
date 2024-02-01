plugins {
    id("com.android.library")
    kotlin("android")
//    id("maven-publish")
    id("com.google.devtools.ksp")
    id("com.github.7hens.ezgradle")
}

android {
    namespace = "com.example.mylibrary"
}

dependencies {
    builtInLibs {
        test()
    }
    implementation("androidx.core:core-ktx")
    implementation("androidx.appcompat:appcompat")
    implementation("com.google.android.material:material")
}