package me.thens.ezgradle

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ModuleVersionSelector

operator fun RepoCoordinate.Companion.invoke(notion: String): RepoCoordinate {
    val (group, name, version) = notion.split(":")
    return RepoCoordinate(group, name, version)
}

operator fun RepoCoordinate.Companion.invoke(dependency: Dependency) = RepoCoordinate(
    group = dependency.group!!,
    name = dependency.name,
    version = dependency.version!!,
)

operator fun RepoCoordinate.Companion.invoke(dependency: ModuleVersionSelector) = RepoCoordinate(
    group = dependency.group,
    name = dependency.name,
    version = dependency.version!!,
)
