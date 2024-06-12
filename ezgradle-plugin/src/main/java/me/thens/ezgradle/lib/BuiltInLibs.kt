package me.thens.ezgradle.lib

import me.thens.ezgradle.model.EzGradleProperties
import me.thens.ezgradle.util.getLibrary
import me.thens.ezgradle.util.toCatalogAlias
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import kotlin.jvm.optionals.getOrNull

class BuiltInLibs(
    private val project: Project,
    private val properties: EzGradleProperties,
    dependencies: DependencyHandler,
) : DependencyHandler by dependencies {

    private fun lib(name: String): Any {
        return properties.versionCatalog?.findLibrary(name.toCatalogAlias())?.getOrNull() ?: name
    }

    fun compose() {
        implementation(lib("androidx.activity:activity-compose"))
        implementation(lib("androidx.compose.ui:ui"))
        implementation(lib("androidx.compose.ui:ui-graphics"))
        implementation(lib("androidx.compose.ui:ui-tooling-preview"))
        implementation(lib("androidx.compose.material:material"))
        implementation(lib("androidx.compose.material3:material3"))
        androidTestImplementation(lib("androidx.compose.ui:ui-test-junit4"))
        debugImplementation(lib("androidx.compose.ui:ui-tooling"))
        debugImplementation(lib("androidx.compose.ui:ui-test-manifest"))
    }

    fun hilt() {
        implementation(lib("com.google.dagger:hilt-android"))
        xapt(lib("com.google.dagger:hilt-android-compiler"))
        xapt(lib("androidx.hilt:hilt-compiler"))
    }

    fun test() {
        testImplementation(lib("junit:junit"))
        androidTestImplementation(lib("androidx.test.ext:junit"))
        androidTestImplementation(lib("androidx.test.espresso:espresso-core"))
    }

    fun xapt(notion: Any) {
        add(kaptName, notion)
    }

    private val kaptName: String by lazy {
        listOf("ksp", "kapt", "annotationProcessor")
            .first { project.configurations.findByName(it) != null }
    }
}