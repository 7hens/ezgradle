package me.thens.ezgradle.lib

import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.applyAndroidConstraints() {
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    constraints {
        implementation("androidx.activity:activity-compose:1.8.0")
        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("androidx.core:core-ktx:1.10.1")
        implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        implementation("com.google.android.material:material:1.9.0")
        val hiltVersion = "2.47"
        implementation("com.google.dagger:hilt-android:$hiltVersion")
        implementation("com.google.dagger:hilt-compiler:$hiltVersion")
        implementation("io.ktor:ktor-client-android:2.3.6")
        testImplementation("junit:junit:4.13.2")
    }
}

