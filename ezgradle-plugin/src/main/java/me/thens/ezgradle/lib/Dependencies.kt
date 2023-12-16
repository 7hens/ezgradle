package me.thens.ezgradle.lib

import org.gradle.api.artifacts.dsl.DependencyConstraintHandler
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.api(notation: Any) = add("api", notation)
fun DependencyHandler.implementation(notation: Any) = add("implementation", notation)
fun DependencyHandler.debugImplementation(notation: Any) = add("debugImplementation", notation)
fun DependencyHandler.testImplementation(notation: Any) = add("testImplementation", notation)
fun DependencyHandler.androidTestImplementation(notation: Any) = add("androidTestImplementation", notation)
fun DependencyHandler.kapt(notation: Any) = add("kapt", notation)

fun DependencyConstraintHandler.api(notion: Any) = add("api", notion)
fun DependencyConstraintHandler.implementation(notation: Any) = add("implementation", notation)
fun DependencyConstraintHandler.debugImplementation(notation: Any) = add("debugImplementation", notation)
fun DependencyConstraintHandler.testImplementation(notation: Any) = add("testImplementation", notation)
fun DependencyConstraintHandler.androidTestImplementation(notation: Any) = add("androidTestImplementation", notation)
fun DependencyConstraintHandler.kapt(notation: Any) = add("kapt", notation)
