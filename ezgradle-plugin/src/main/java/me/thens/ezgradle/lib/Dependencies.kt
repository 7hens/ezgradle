package me.thens.ezgradle.lib

import org.gradle.api.artifacts.dsl.DependencyConstraintHandler
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.androidTestImplementation(notation: Any) = add("androidTestImplementation", notation)
fun DependencyHandler.annotationProcessor(notation: Any) = add("annotationProcessor", notation)
fun DependencyHandler.api(notation: Any) = add("api", notation)
fun DependencyHandler.compileOnly(notation: Any) = add("compileOnly", notation)
fun DependencyHandler.compileOnlyApi(notation: Any) = add("compileOnlyApi", notation)
fun DependencyHandler.debugImplementation(notation: Any) = add("debugImplementation", notation)
fun DependencyHandler.implementation(notation: Any) = add("implementation", notation)
fun DependencyHandler.kapt(notation: Any) = add("kapt", notation)
fun DependencyHandler.ksp(notation: Any) = add("ksp", notation)
fun DependencyHandler.runtimeOnly(notion: Any) = add("runtimeOnly", notion)
fun DependencyHandler.testCompileOnly(notation: Any) = add("testCompileOnly", notation)
fun DependencyHandler.testCompileClasspath(notation: Any) = add("testCompileClasspath", notation)
fun DependencyHandler.testImplementation(notation: Any) = add("testImplementation", notation)
fun DependencyHandler.testRuntimeOnly(notation: Any) = add("testRuntimeOnly", notation)
fun DependencyHandler.testRuntimeClasspath(notation: Any) = add("testRuntimeClasspath", notation)

fun DependencyConstraintHandler.androidTestImplementation(notation: Any) = add("androidTestImplementation", notation)
fun DependencyConstraintHandler.annotationProcessor(notation: Any) = add("annotationProcessor", notation)
fun DependencyConstraintHandler.api(notation: Any) = add("api", notation)
fun DependencyConstraintHandler.compileOnly(notation: Any) = add("compileOnly", notation)
fun DependencyConstraintHandler.compileOnlyApi(notation: Any) = add("compileOnlyApi", notation)
fun DependencyConstraintHandler.debugImplementation(notation: Any) = add("debugImplementation", notation)
fun DependencyConstraintHandler.implementation(notation: Any) = add("implementation", notation)
fun DependencyConstraintHandler.kapt(notation: Any) = add("kapt", notation)
fun DependencyConstraintHandler.ksp(notation: Any) = add("ksp", notation)
fun DependencyConstraintHandler.runtimeOnly(notion: Any) = add("runtimeOnly", notion)
fun DependencyConstraintHandler.testCompileOnly(notation: Any) = add("testCompileOnly", notation)
fun DependencyConstraintHandler.testCompileClasspath(notation: Any) = add("testCompileClasspath", notation)
fun DependencyConstraintHandler.testImplementation(notation: Any) = add("testImplementation", notation)
fun DependencyConstraintHandler.testRuntimeOnly(notation: Any) = add("testRuntimeOnly", notation)
fun DependencyConstraintHandler.testRuntimeClasspath(notation: Any) = add("testRuntimeClasspath", notation)

