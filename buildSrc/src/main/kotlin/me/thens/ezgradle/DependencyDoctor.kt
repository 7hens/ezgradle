package me.thens.ezgradle

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.gradle.api.Project

class DependencyDoctor(private val project: Project) {
    fun run() {
//        logAll()
        testMavenSearch()
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

    private fun testMavenSearch() {
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        runBlocking {
//            api(platform("androidx.compose:compose-bom:2023.10.01"))
            println("junit: " + MavenSearch.queryLatestVersion("junit", "junit"))
        }
    }
}