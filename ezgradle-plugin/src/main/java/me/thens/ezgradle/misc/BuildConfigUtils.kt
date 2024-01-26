package me.thens.ezgradle.misc

import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import java.io.File
import java.util.Properties

private fun File.isUpToDate(inputFiles: List<File>): Boolean {
    return exists() && inputFiles.all { it.lastModified() < lastModified() }
}

val Project.buildConfigProps
    get() = extra.getOrPut("ezgradle.buildConfig") { initBuildConfig() }

private fun Project.initBuildConfig(): Map<String, Any> {
    val gradleKeys = Properties().run {
        rootProject.file("gradle.properties").takeIf { it.isFile }?.inputStream()?.use { load(it) }
        keys
    }
    val myProps = Properties().apply {
        properties.forEach { (k, v) -> k.takeIf { it in gradleKeys }?.let { put(k, v ?: "") } }
        rootProject.file("local.properties").takeIf { it.isFile }?.inputStream()?.use { load(it) }
    }
    return mutableMapOf<String, Any>().apply {
        myProps.forEach { (key, value) ->
            key.toString().takeIf { it.matches(Regex("[A-Z_]+")) }?.let { put(it, value ?: "") }
        }
        put("GROUP", project.group)
        put("NAME", project.name)
        put("VERSION", project.version)
    }
}

fun Project.generateBuildConfig(packageName: String) {
    val className = "BuildConfig"
    val packageDir = packageName.replace('.', '/')
    val outputDir = layout.buildDirectory.file("generated/src/main/kotlin/").get().asFile
    val outputFile = File(outputDir, "$packageDir/$className.kt")
    val inputFiles = listOf("gradle.properties", "local.properties", "build.gradle.kts")
        .flatMap { listOf(project.file(it), rootProject.file(it)) }
        .filter { it.isFile }
    if (!outputFile.isUpToDate(inputFiles)) {
        outputFile.parentFile?.mkdirs()
        val fields = buildConfigProps.entries
            .joinToString("\n   ") { "val ${it.key} = \"${it.value}\"" }
        outputFile.writeText(
            """
            |package $packageName
            |
            |object $className {
            |   $fields
            |}
            """.trimMargin("|")
        )
    }
}
