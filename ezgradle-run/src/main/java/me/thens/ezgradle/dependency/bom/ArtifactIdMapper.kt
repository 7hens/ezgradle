package me.thens.ezgradle.dependency.bom

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ModuleVersionSelector

operator fun ArtifactKey.Companion.invoke(notion: String): ArtifactKey {
    val (group, name, version) = notion.split(":")
    return ArtifactKey(group, name, version)
}

operator fun ArtifactKey.Companion.invoke(dependency: Dependency) = ArtifactKey(
    group = dependency.group!!,
    name = dependency.name,
    version = dependency.version!!,
)

operator fun ArtifactKey.Companion.invoke(dependency: ModuleVersionSelector) = ArtifactKey(
    group = dependency.group,
    name = dependency.name,
    version = dependency.version!!,
)
