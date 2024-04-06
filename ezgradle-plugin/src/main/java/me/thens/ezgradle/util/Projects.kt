package me.thens.ezgradle.util

import org.gradle.api.Project
import java.io.File
import java.util.Properties

val Project.isIncludedBuild: Boolean
    get() {
        return name == "buildSrc" || gradle.includedBuilds.any { it.name == name }
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
