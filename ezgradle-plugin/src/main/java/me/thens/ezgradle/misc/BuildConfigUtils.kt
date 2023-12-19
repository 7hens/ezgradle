package me.thens.ezgradle.misc

import org.gradle.api.Project
import java.io.File

private fun File.isUpToDate(inputFiles: List<File>): Boolean {
    return exists() && inputFiles.all { it.lastModified() < lastModified() }
}

fun Project.generateBuildConfig(packageName: String) {
    val className = "ProjectBuildConfig"
    val packageDir = packageName.replace('.', '/')
    val outputDir = layout.buildDirectory.file("generated/src/main/kotlin/").get().asFile
    val outputFile = File(outputDir, "$packageDir/$className.kt")
    val inputFiles = listOf("gradle.properties", "local.properties", "build.gradle.kts")
        .flatMap { listOf(project.file(it), rootProject.file(it)) }
        .filter { it.isFile }
    if (!outputFile.isUpToDate(inputFiles)) {
        outputFile.parentFile?.mkdirs()
        val props = mutableMapOf<String, Any?>().apply {
            putAll(properties)
            put("GROUP", project.group)
            put("NAME", project.name)
            put("VERSION", project.version)
        }
        val fields = props.entries
            .filter { it.key.matches(Regex("[0-9A-Z_]+")) }
            .distinctBy { it.key }
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
