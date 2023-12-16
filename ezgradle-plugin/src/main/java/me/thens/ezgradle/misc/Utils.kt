package me.thens.ezgradle.misc

import org.gradle.api.Project

fun kotlin(name: String) = "org.jetbrains.kotlin.$name"

fun versionNameToCode(version: String): Int {
    return version.split(".")
        .map { it.toIntOrNull() ?: 0 }
        .plus(listOf(0, 0, 0))
        .take(3)
        .fold(0) { acc, i -> acc * 1000 + i }
}

fun <T> Project.configure(name: String, fn: T.() -> Unit) {
    extensions.configure(name, fn)
}
