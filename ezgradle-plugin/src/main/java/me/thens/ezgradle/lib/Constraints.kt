package me.thens.ezgradle.lib

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.platform() {
    bom("androidx.compose:compose-bom:2023.08.00")
    bom("io.github.jan-tennert.supabase:bom:1.4.7")
    constraints {
        api("androidx.activity:activity-compose:1.8.0")
        api("androidx.core:core-ktx:1.10.1")
        api("androidx.hilt:hilt-navigation-compose:1.1.0")
        api("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
        api("androidx.test.ext:junit:1.1.5")
        api("androidx.test.espresso:espresso-core:3.5.1")
        val hiltVersion = "2.47"
        api("com.google.dagger:hilt-android:$hiltVersion")
        api("com.google.dagger:hilt-compiler:$hiltVersion")
        api("io.ktor:ktor-client-android:2.3.6")
        api("junit:junit:4.13.2")
    }
}

