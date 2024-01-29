package me.thens.ezgradle.lib

import me.thens.ezgradle.BuildConfig
import me.thens.ezgradle.misc.hasConfiguration
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class EzGradleBomManager(val project: Project) {

    fun addDependencies(version: String = BuildConfig.VERSION) {
        project.dependencies {
            val bom = platform("${BuildConfig.GROUP}:$EZGRADLE_BOM:$version")
            configurations
                .filter { project.hasConfiguration(it) }
                .forEach { add(it, bom) }
        }
    }

    companion object {
        private const val EZGRADLE_BOM = "ezgradle-bom"

        private val configurations = listOf(
            "implementation",
            "androidTestImplementation",
            "kapt",
            "annotationProcessor",
        )

    }
}