package me.thens.ezgradle.misc

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.register
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Properties

abstract class GenerateBuildConfigTask : DefaultTask() {
    @get:Input
    abstract val packageName: Property<String>

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    init {
        packageName.convention(project.getDefaultPackageName())
        outputFile.convention {
            val packageDir = packageName.get().replace('.', '/')
            val outputDir = project.file(OUTPUT_DIR)
            File(outputDir, "$packageDir/$CLASS_NAME.kt")
        }
    }

    @Input
    fun getBuildConfigProps(): Map<String, Any> {
        return getProperties(project)
    }

    @TaskAction
    fun generateBuildConfig() {
        val fields = getBuildConfigProps().entries.joinToString("\n   ") {
            "const val ${it.key} = \"${it.value}\""
        }
        outputFile.get().asFile.apply {
            parentFile?.mkdirs()
            writeText(
                """
                |package $packageName
                |
                |object $CLASS_NAME {
                |   $fields
                |}
                """.trimMargin("|")
            )
        }
    }

    companion object {
        private const val OUTPUT_DIR = "build/generated/src/main/kotlin"
        private const val TASK_NAME = "generateBuildConfig"
        private const val CLASS_NAME = "BuildConfig"

        fun register(project: Project) {
            project.afterEvaluate {
                tasks.register<GenerateBuildConfigTask>(TASK_NAME) {
                    group = "build"
                    tasks.filter { it.name.startsWith("compile") && it.name.endsWith("Kotlin") }
                        .forEach { it.dependsOn(this) }
                }
            }
        }

        fun getProperties(project: Project): Map<String, Any> {
            val gradleKeys = Properties().run {
                load(project.rootProject.file("gradle.properties"))
                keys
            }
            val myProps = Properties().apply {
                project.properties.forEach { (key, value) ->
                    key.takeIf { it in gradleKeys }?.let { put(key, value ?: "") }
                }
                load(project.rootProject.file("local.properties"))
            }
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            return mutableMapOf<String, Any>().apply {
                myProps.forEach { (key, value) ->
                    key.toString()
                        .takeIf { it.matches(Regex("[A-Z_]+")) }
                        ?.let { put(it, value ?: "") }
                }
                put("GROUP", project.group)
                put("NAME", project.name)
                put("VERSION", project.version)
                put("BUILD_TIME", dateFormat.format(Date()))
            }
        }

        private fun Properties.load(file: File) {
            file.takeIf { it.isFile }?.inputStream()?.use { load(it) }
        }

    }
}