package me.thens.ezgradle.lib

import me.thens.ezgradle.util.hasConfiguration
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class EzGradleBomManager(val project: Project) {

    fun addDependencies(version: String) {
        project.dependencies {
            val bom = platform("$GROUP:$EZGRADLE_BOM:$version")
            configurations
                .filter { project.hasConfiguration(it) }
                .forEach { add(it, bom) }
        }
    }

    companion object {
        private const val GROUP: String = "com.github.7hens.ezgradle"
        private const val EZGRADLE_BOM = "ezgradle-bom"

        private val configurations = listOf(
            "implementation",
            "androidTestImplementation",
            "kapt",
            "annotationProcessor",
        )

    }
}