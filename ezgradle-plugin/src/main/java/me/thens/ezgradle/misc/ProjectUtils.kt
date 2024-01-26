package me.thens.ezgradle.misc

import org.gradle.api.Project
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.kotlin.dsl.extra

fun Project.loadProperties(path: String) {
    val properties = java.util.Properties()
    listOf(rootProject.file(path), project.file(path)).forEach { file ->
        if (file.exists()) {
            properties.load(file.inputStream())
        }
    }
    properties.forEach { key, value -> extra[key.toString()] = value }
}

fun <T> ExtraPropertiesExtension.getOrElse(name: String, defaultVal: () -> T): T {
    return if (has(name)) get(name) as T else defaultVal()
}

fun <T> ExtraPropertiesExtension.getOrPut(name: String, defaultVal: () -> T): T {
    return getOrElse(name) { defaultVal().also { set(name, it) } }
}

fun <T> Project.configure(name: String, fn: T.() -> Unit) {
    extensions.configure(name, fn)
}

val Project.isAndroid: Boolean get() = extensions.findByName("android") != null

val Project.isJava: Boolean get() = extensions.findByName("java") != null

val Project.isJavaPlatform: Boolean get() = extensions.findByName("javaPlatform") != null

val Project.isRoot: Boolean get() = projectDir == rootDir
