package me.thens.ezgradle.model

import dev.horizona.core.log.LogLevel
import me.thens.ezgradle.util.getOrPut
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.getByType
import kotlin.jvm.optionals.getOrNull

data class EzGradleProperties(
    val logLevel: LogLevel,
    val libsName: String,
    val enableBom: Boolean,
    val generateBuildConfig: Boolean,
    val versionCatalog: VersionCatalog?,
    val libs: EzGradleLibs,
) {

    companion object {
        const val GENERATED_DIR = "build/generated/source/ezgradle/main/java"
        private const val PROP_KEY = "EZGRADLE_PROPERTIES"

        fun from(project: Project): EzGradleProperties {
            return project.extra.getOrPut(PROP_KEY) { createFrom(project) }
        }

        private fun createFrom(project: Project): EzGradleProperties {
            return ProjectProperties.from(project).with("ezgradle.").run {
                val libsName = get("libsName") ?: "ezLibs"
                val versionCatalogs = project.extensions.getByType<VersionCatalogsExtension>()
                val versionCatalog = versionCatalogs.find(libsName).getOrNull()
                EzGradleProperties(
                    logLevel = getEnum("logLevel", LogLevel.INFO),
                    libsName = libsName,
                    enableBom = get("enableBom")?.toBoolean() ?: true,
                    generateBuildConfig = get("generateBuildConfig")?.toBoolean() ?: true,
                    versionCatalog = versionCatalog,
                    libs = versionCatalog?.let { EzGradleLibs.from(it) } ?: EzGradleLibs(),
                )
            }
        }
    }
}