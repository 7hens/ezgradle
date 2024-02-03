package me.thens.ezgradle.dependency.model

import kotlinx.serialization.Serializable
import me.thens.ezgradle.dependency.bom.ArtifactKey

@Serializable
data class DependencyGraph(
    val dependencies: Map<ArtifactKey, Set<ArtifactKey>>,
    val versions: Map<ArtifactKey, Set<ArtifactKey>>,
) {

    class Builder {
        private val dependencies = mutableMapOf<ArtifactKey, Set<ArtifactKey>>()
        private val versions = mutableMapOf<ArtifactKey, MutableSet<ArtifactKey>>()

        fun addAll(graph: DependencyGraph) {
            graph.dependencies.forEach { (key, value) -> add(key, value) }
        }

        fun add(key: ArtifactKey, dependencies: Set<ArtifactKey>) {
            this.dependencies[key] = dependencies
            addVersion(key)
            dependencies.forEach { addVersion(it) }
        }

        private fun addVersion(key: ArtifactKey) {
            versions.getOrPut(key.unspecified()) { mutableSetOf() }.add(key)
        }

        operator fun contains(key: ArtifactKey): Boolean {
            return key.unspecified() in versions
        }

        operator fun get(key: ArtifactKey): Set<ArtifactKey> {
            return dependencies[key] ?: emptySet()
        }

        fun getDescendants(key: ArtifactKey): Set<ArtifactKey> {
            return get(key).flatMap { getDescendants(it) + key }.toSet()
        }

        fun build(): DependencyGraph {
            return DependencyGraph(dependencies, versions)
        }
    }
}