package me.thens.ezgradle.dependency.data

import me.thens.ezgradle.dependency.bom.ArtifactKey

interface RepositoryRepo {
    suspend fun queryDependencies(artifactKey: ArtifactKey): List<ArtifactKey>

    suspend fun queryLatestVersion(group: String, name: String): String?

    suspend fun queryLatestVersion(artifactKey: ArtifactKey): String? {
        return queryLatestVersion(artifactKey.group, artifactKey.name)
    }
}