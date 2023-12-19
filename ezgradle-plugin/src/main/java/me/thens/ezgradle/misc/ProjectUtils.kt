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

fun Project.extra(name: String, defaultVal: Any = ""): String {
    if (extra.has(name)) {
        return extra[name].toString()
    }
    return defaultVal.toString()
}

fun <T> Project.configure(name: String, fn: T.() -> Unit) {
    extensions.configure(name, fn)
}

val Project.isAndroid: Boolean get() = extensions.findByName("android") != null

val Project.isJava: Boolean get() = extensions.findByName("java") != null

val Project.isJavaPlatform: Boolean get() = extensions.findByName("javaPlatform") != null

val Project.isRoot: Boolean get() = projectDir == rootDir
