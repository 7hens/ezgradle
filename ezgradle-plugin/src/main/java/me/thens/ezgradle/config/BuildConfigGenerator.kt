package me.thens.ezgradle.config

import me.thens.ezgradle.model.EzGradleProperties
import me.thens.ezgradle.model.ProjectProperties
import me.thens.ezgradle.util.getDefaultPackageName
import me.thens.ezgradle.util.loadProperties
import me.thens.ezgradle.util.mapProperties
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.create
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

    companion object {
        private const val OUTPUT_DIR = EzGradleProperties.GENERATED_DIR
        private const val TASK_NAME = "generateBuildConfig"
        private const val CLASS_NAME = "BuildConfig"

        fun registerTask(project: Project) {
            val regex = Regex(".*Kotlin.*")
            val generator = BuildConfigGenerator(project)
            val outputDir = project.file(OUTPUT_DIR)
            project.afterEvaluate {
                tasks.create<Task>(TASK_NAME).apply {
                    packageName.convention(project.getDefaultPackageName())
                    properties.convention(project.objects.mapProperties(generator.getProperties()))
                    outputFile.convention { getOutputFile(outputDir, packageName.get()) }
                    project.tasks
                        .filter { it.name.matches(regex) }
                        .forEach { it.dependsOn(this) }
                }
            }
        }

        private fun getOutputFile(outputDir: File, packageName: String): File {
            val packageDir = packageName.replace('.', '/')
            return File(outputDir, "$packageDir/${CLASS_NAME}.java")
        }
    }

    abstract class Task : DefaultTask() {

        @get:Input
        abstract val packageName: Property<String>

        @get:Input
        abstract val properties: MapProperty<String, String>

        @get:OutputFile
        abstract val outputFile: RegularFileProperty

        @TaskAction
        fun generateBuildConfig() {
            val fields = properties.get().entries.joinToString("\n   ") {
                "public static final String ${it.key} = \"${it.value}\";"
            }
            outputFile.get().asFile.apply {
                parentFile?.mkdirs()
                writeText(
                    """
                    |package ${packageName.get()};
                    |
                    |public class $CLASS_NAME {
                    |   $fields
                    |}
                    """.trimMargin("|")
                )
            }
        }
    }
}