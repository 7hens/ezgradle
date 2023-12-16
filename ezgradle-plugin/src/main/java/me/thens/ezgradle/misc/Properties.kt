package me.thens.ezgradle.misc

import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

fun Project.loadProperties(path: String) {
    val properties = java.util.Properties()
    listOf(rootProject.file(path), project.file(path)).forEach {  file ->
        if (file.exists()) {
            properties.load(file.inputStream())
        }
    }
    properties.forEach { key, value -> extra[key.toString()] = value }
}

fun Project.extra(name: String): String {
    if (extra.has(name)) {
        return extra[name].toString()
    }
    return ""
}