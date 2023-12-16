package me.thens.ezgradle.lib

import org.gradle.api.artifacts.dsl.DependencyConstraintHandler
import org.gradle.api.artifacts.dsl.DependencyHandler
import java.io.File

fun DependencyHandler.api(notation: Any) = add("api", notation)
fun DependencyHandler.implementation(notation: Any) = add("implementation", notation)
fun DependencyHandler.debugImplementation(notation: Any) = add("debugImplementation", notation)
fun DependencyHandler.testImplementation(notation: Any) = add("testImplementation", notation)
fun DependencyHandler.androidTestImplementation(notation: Any) = add("androidTestImplementation", notation)
fun DependencyHandler.kapt(notation: Any) = add("kapt", notation)
fun DependencyHandler.bom(notion: Any) = api(platform(notion))
fun DependencyConstraintHandler.api(notion: Any) = add("api", notion)

fun DependencyHandler.resolveVersions(file: File) {
    if (!file.isFile) {
        return
    }
    constraints {
        file.bufferedReader().use { reader ->
            reader.lineSequence()
                .filter { it.isNotBlank() }
                .forEach { api(it) }
        }
    }
}
