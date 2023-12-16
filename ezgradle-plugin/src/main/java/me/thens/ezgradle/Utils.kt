package me.thens.ezgradle

import org.gradle.api.Project

fun kotlin(name: String) = "org.jetbrains.kotlin.$name"

fun versionNameToCode(version: String): Int {
    return version.split(".")
        .map { it.toIntOrNull() ?: 0 }
        .plus(listOf(0, 0, 0))
        .take(3)
        .fold(0) { acc, i -> acc * 100 + i }
}

fun <T> Project.ext(name: String, fn: T.() -> Unit) {
    extensions.configure(name, fn)
}
