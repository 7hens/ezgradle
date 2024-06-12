package me.thens.ezgradle.util

import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency
import kotlin.jvm.optionals.getOrNull

fun VersionCatalog.getVersion(name: String): String {
    return findVersion(name).get().requiredVersion
}

fun VersionCatalog.getVersion(name: String, defaultValue: String): String {
    return findVersion(name).getOrNull()?.requiredVersion ?: defaultValue
}

fun VersionCatalog.getLibrary(name: String): Provider<MinimalExternalModuleDependency> {
    return findLibrary(name).get()
}

fun VersionCatalog.getPlugin(name: String): Provider<PluginDependency> {
    return findPlugin(name).get()
}

fun VersionCatalog.getBundle(name: String): Provider<ExternalModuleDependencyBundle> {
    return findBundle(name).get()
}
