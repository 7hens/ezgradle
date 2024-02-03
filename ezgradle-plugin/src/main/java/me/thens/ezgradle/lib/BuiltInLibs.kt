package me.thens.ezgradle.lib

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

class BuiltInLibs(val project: Project, dependencies: DependencyHandler) :
    DependencyHandler by dependencies {

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
        add(ap, "com.google.dagger:hilt-android-compiler")
        add(ap, "androidx.hilt:hilt-compiler")
    }

    fun test() {
        testImplementation("junit:junit")
        androidTestImplementation("androidx.test.ext:junit")
        androidTestImplementation("androidx.test.espresso:espresso-core")
    }

    private val ap: String by lazy {
        listOf("ksp", "kapt", "annotationProcessor")
            .first { project.configurations.findByName(it) != null }
    }
}