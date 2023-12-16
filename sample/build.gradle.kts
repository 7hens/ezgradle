import me.thens.ezgradle.builtInLibs

plugins {
    id("com.android.application")
    kotlin("android")
    id("me.thens.ezgradle")
}

android {
    println("configure android in build.gradle.kts")
    namespace = "me.thens.ezgradle"
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
}

println("out dependencies")

afterEvaluate {
    println("afterEvaluate")
}