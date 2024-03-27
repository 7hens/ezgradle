package me.thens.ezgradle.util

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

@Suppress("UNCHECKED_CAST")
fun <T> ExtraPropertiesExtension.getOrElse(name: String, defaultVal: () -> T): T {
    return if (has(name)) get(name) as T else defaultVal()
}

fun <T> ExtraPropertiesExtension.getOrPut(name: String, defaultVal: () -> T): T {
    return getOrElse(name) { defaultVal().also { set(name, it) } }
}

fun <T> Project.configure(name: String, fn: T.() -> Unit) {
    extensions.configure(name, fn)
}

fun Project.getDefaultPackageName(): String {
    return "$group.$name".toPackageName()
}

fun Project.hasConfiguration(name: String): Boolean = configurations.findByName(name) != null

val Project.isAndroid: Boolean get() = extensions.findByName("android") != null

val Project.isJava: Boolean get() = extensions.findByName("java") != null

val Project.isJavaPlatform: Boolean get() = extensions.findByName("javaPlatform") != null

val Project.isVersionCatalog: Boolean get() = extensions.findByName("catalog") != null

val Project.isRoot: Boolean get() = projectDir == rootDir
