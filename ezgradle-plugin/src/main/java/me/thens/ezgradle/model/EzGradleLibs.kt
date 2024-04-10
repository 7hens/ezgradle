package me.thens.ezgradle.model

import me.thens.ezgradle.util.getVersion
import org.gradle.api.artifacts.VersionCatalog

data class EzGradleLibs(
    val versions: Versions = Versions(),
) {
    companion object {
        fun from(versionCatalog: VersionCatalog): EzGradleLibs {
            return versionCatalog.run {
                val v = Versions()
                EzGradleLibs(
                    versions = Versions(
                        androidMinSdk = getVersion("android-min-sdk", v.androidMinSdk),
                        androidTargetSdk = getVersion("android-target-sdk", v.androidTargetSdk),
                        composeCompiler = getVersion("compose-compiler", v.composeCompiler),
                        ezgradle = getVersion("ezgradle", v.ezgradle),
                    ),
                )
            }
        }
    }

    data class Versions(
        val androidMinSdk: String = "21",
        val androidTargetSdk: String = "34",
        val composeCompiler: String = "1.5.5",
        val ezgradle: String = "2024.03.01-SNAPSHOT",
    )
}