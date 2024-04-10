package me.thens.ezgradle.config

import me.thens.ezgradle.model.ProjectProperties
import me.thens.ezgradle.util.getDefaultPackageName
import me.thens.ezgradle.util.loadProperties
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

class BuildConfigGenerator(private val project: Project) {

    private fun String.matchesPropKey(): Boolean {
        return matches(Regex("[A-Z_]+"))
    }

    fun getProperties(): Map<String, String> {
        val gradleProperties = project.loadProperties("gradle.properties")
        val localProperties = project.loadProperties("local.properties")
        val keys = (gradleProperties.keys + localProperties.keys).filter { it.matchesPropKey() }
        val projectProperties = ProjectProperties(project, localProperties, "")
        return keys.associateWith { projectProperties.getValue(it) } + getExtraProperties()
    }

    private fun getExtraProperties(): Map<String, String> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        return mapOf(
            "GROUP" to project.group.toString(),
            "NAME" to project.name,
            "VERSION" to project.version.toString(),
            "BUILD_TIME" to dateFormat.format(Date()),
        )
    }

    fun getOutputFile(packageName: String): File {
        val packageDir = packageName.replace('.', '/')
        val outputDir = project.file(OUTPUT_DIR)
        return File(outputDir, "$packageDir/${CLASS_NAME}.java")
    }

    fun generate(
        properties: Map<String, String> = getProperties(),
        packageName: String = project.getDefaultPackageName(),
        outputFile: File = getOutputFile(packageName),
    ) {
        val fields = properties.entries.joinToString("\n   ") {
            "public static final String ${it.key} = \"${it.value}\";"
        }
        outputFile.apply {
            parentFile?.mkdirs()
            writeText(
                """
                |package ${packageName};
                |
                |public class $CLASS_NAME {
                |   $fields
                |}
                """.trimMargin("|")
            )
        }
    }

    companion object {
        private const val OUTPUT_DIR = "build/generated/source/ezgradle/main/java"
        private const val TASK_NAME = "generateBuildConfig"
        private const val CLASS_NAME = "BuildConfig"

        fun registerTask(project: Project) {
            project.afterEvaluate {
                tasks.register<Task>(TASK_NAME) {
                    initProperties()
                    project.tasks.filter { it.name.startsWith("compile") && it.name.contains("Kotlin") }
                        .forEach { it.dependsOn(this) }
                }
            }
        }
    }

    abstract class Task : DefaultTask() {
        private val generator by lazy { BuildConfigGenerator(project) }

        @get:Input
        abstract val packageName: Property<String>

        @get:OutputFile
        abstract val outputFile: RegularFileProperty

        @Input
        fun getBuildConfigProps(): Map<String, String> {
            return generator.getProperties()
        }

        internal fun initProperties() {
            packageName.convention(project.getDefaultPackageName())
            outputFile.convention { generator.getOutputFile(packageName.get()) }
        }

        @TaskAction
        fun generateBuildConfig() {
            generator.generate(getBuildConfigProps(), packageName.get(), outputFile.get().asFile)
        }
    }
}