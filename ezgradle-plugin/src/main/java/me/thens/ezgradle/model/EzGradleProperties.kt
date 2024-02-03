package me.thens.ezgradle.model

import com.github.a7hens.ezgradle.com.github.a7hens.ezgradle.gradle.plugin.BuildConfig
import org.gradle.api.Project

data class EzGradleProperties(
    val bomVersion: String,
    val generateBuildConfig: Boolean,
) {

    companion object {
        fun from(project: Project): EzGradleProperties {
            return ProjectProperties(project, "ezgradle.").run {
                EzGradleProperties(
                    bomVersion = getString("bomVersion") ?: BuildConfig.VERSION,
                    generateBuildConfig = getBoolean("generateBuildConfig") ?: true,
                )
            }
        }
    }
}