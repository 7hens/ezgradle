package me.thens.ezgradle.model

import com.github.a7hens.ezgradle.com.github.a7hens.ezgradle.gradle.plugin.BuildConfig
import org.gradle.api.Project

data class EzGradleProperties(
    val bomVersion: String,
    val enableBom: Boolean,
    val generateBuildConfig: Boolean,
) {

    companion object {
        fun from(project: Project): EzGradleProperties {
            return ProjectProperties.from(project).with("ezgradle.").run {
                EzGradleProperties(
                    bomVersion = get("bomVersion") ?: BuildConfig.VERSION,
                    enableBom = get("enableBom")?.toBoolean() ?: false,
                    generateBuildConfig = get("generateBuildConfig")?.toBoolean() ?: true,
                )
            }
        }
    }
}