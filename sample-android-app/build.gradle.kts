plugins {
    id("com.android.application")
    kotlin("android")
    id("me.thens.ezgradle")
}

dependencies {
    builtInLibs {
        compose()
        test()
    }
    implementation("androidx.core:core-ktx")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android")
}