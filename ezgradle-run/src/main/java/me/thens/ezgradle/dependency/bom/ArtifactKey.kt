package me.thens.ezgradle.dependency.bom

import kotlinx.serialization.Serializable

@Serializable
data class ArtifactKey(
    val group: String,
    val name: String,
    val version: String = "",
) {
    val isUnspecified: Boolean get() = version.isEmpty()

    val isGradlePlugin: Boolean get() = name == "$group.gradle.plugin"

    fun unspecified(): ArtifactKey {
        return ArtifactKey(group, name)
    }

    override fun toString(): String {
        return if (isUnspecified) "$group:$name" else "$group:$name:$version"
    }

    companion object
}
