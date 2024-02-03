package me.thens.ezgradle.dependency.bom

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import java.io.File

class DependencyBomManager(
    private val project: Project,
    private val json: Json = Json,
) {
    fun include(dependencyBom: DependencyBom) {
        project.dependencies {
            dependencyBom.apply {
                platforms.forEach { add("api", platform(it.toString())) }
                constraints {
                    plugins.forEach { add("api", it.toString()) }
                    dependencies.forEach { add("api", it.toString()) }
                }
            }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun output(dependencyBom: DependencyBom, file: File) {
        file.outputStream().use { json.encodeToStream(dependencyBom, it) }
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun parse(file: File): DependencyBom {
        return file.inputStream().use { json.decodeFromStream<DependencyBom>(it) }
    }

    fun parseProject(): DependencyBom {
        return project.configurations.run {
            val platforms = flatMap { it.dependencies }.map { ArtifactKey(it) }
            val constrains = flatMap { it.dependencyConstraints }.map { ArtifactKey(it) }
            val (plugins, dependencies) = constrains.partition { it.isGradlePlugin }
            DependencyBom(
                platforms = platforms.map { it.toString() }.sorted(),
                plugins = plugins.map { it.toString() }.sorted(),
                dependencies = dependencies.map { it.toString() }.sorted(),
            )
        }
    }
}