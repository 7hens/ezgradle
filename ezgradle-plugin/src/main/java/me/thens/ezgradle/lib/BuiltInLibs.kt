package me.thens.ezgradle.lib

import org.gradle.api.artifacts.dsl.DependencyHandler

class BuiltInLibs(dependencies: DependencyHandler): DependencyHandler by dependencies {

    fun compose() {
        implementation("androidx.activity:activity-compose")
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material:material")
        implementation("androidx.compose.material3:material3")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
    }

    fun hilt() {
        implementation("com.google.dagger:hilt-android")
//        kapt("com.google.dagger:hilt-compiler")
        kapt("com.google.dagger:hilt-android-compiler")
        kapt("androidx.hilt:hilt-compiler")
//    implementation("androidx.hilt:hilt-navigation-compose")
    }

    fun test() {
        testImplementation("junit:junit")
        androidTestImplementation("androidx.test.ext:junit")
        androidTestImplementation("androidx.test.espresso:espresso-core")
    }
}