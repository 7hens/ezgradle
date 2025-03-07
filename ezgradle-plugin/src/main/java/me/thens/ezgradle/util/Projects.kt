package me.thens.ezgradle.util

import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.api.provider.MapProperty
import org.gradle.kotlin.dsl.mapProperty
import java.io.File
import java.util.Properties

val Project.isIncludedBuild: Boolean
    get() {
        if (name == "buildSrc") {
            return true
        }
        return try {
            gradle.includedBuilds.any { it.name == name }
        } catch (_: Exception) {
            return false
        }
    }

val Project.globalRootDir: File
    get() {
        return if (isIncludedBuild) rootDir.parentFile else rootDir
    }

fun Project.globalRootFile(fileName: String): File {
    return File(globalRootDir, fileName)
}

fun Project.loadProperties(fileName: String): Map<String, String> {
    return Properties()
        .load(globalRootFile(fileName))
        .toStringMap()
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
    val groupName = group.toString().takeIf { it.isNotBlank() } ?: "dev.${rootProject.name}"
    return "$groupName.$name".toPackageName()
}

fun Project.hasConfiguration(name: String): Boolean = configurations.findByName(name) != null

val Project.isAndroid: Boolean get() = extensions.findByName("android") != null

val Project.isJava: Boolean get() = extensions.findByName("java") != null

val Project.isJavaPlatform: Boolean get() = extensions.findByName("javaPlatform") != null

val Project.isVersionCatalog: Boolean get() = extensions.findByName("catalog") != null

val Project.isRoot: Boolean get() = projectDir == rootDir

fun Project.libVersion(name: String): String {
    TODO()
}

inline fun <reified K, reified V> ObjectFactory.mapProperties(map: Map<K, V>): MapProperty<K, V> {
    return mapProperty<K, V>().apply { putAll(map) }
}
