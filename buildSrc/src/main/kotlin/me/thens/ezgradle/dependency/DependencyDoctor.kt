package me.thens.ezgradle.dependency

import me.thens.ezgradle.dependency.bom.DependencyBom
import me.thens.ezgradle.dependency.bom.DependencyBomManager
import org.gradle.api.Project

class DependencyDoctor(private val project: Project) {
    fun run() {
        logAll()
        DependencyBomManager(project).apply {
            project.file("__local").mkdirs()
            output(parseProject(), project.file("__local/ezgradle-bom.json"))
        }
    }

    fun logAll() {
        project.configurations.flatMap { it.dependencies }.forEach { dependency ->
            val group = dependency.group
            val name = dependency.name
            val version = dependency.version
            println("Dependency: $group:$name:$version")
        }
        project.configurations.flatMap { it.dependencyConstraints }.forEach { dependency ->
            val group = dependency.group
            val name = dependency.name
            val version = dependency.version
            println("Constraint: $group:$name:$version")
        }
    }
}