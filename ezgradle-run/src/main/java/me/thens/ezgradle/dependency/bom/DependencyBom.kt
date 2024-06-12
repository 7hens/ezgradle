package me.thens.ezgradle.dependency.bom

import kotlinx.serialization.Serializable

@Serializable
data class DependencyBom(
    val platforms: List<String>,
    val plugins: List<String>,
    val dependencies: List<String>,
)
