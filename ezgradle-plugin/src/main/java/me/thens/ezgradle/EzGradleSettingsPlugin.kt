package me.thens.ezgradle

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.initialization.resolve.RepositoriesMode
import org.gradle.kotlin.dsl.maven

@Suppress("unused")
class EzGradleSettingsPlugin : Plugin<Settings> {
    override fun apply(target: Settings) {
        println("#>> apply plugin ezgradle settings")
        target.configure()
    }

    @Suppress("UnstableApiUsage")
    private fun Settings.configure() {
        pluginManagement {
            repositories {
                mavenLocal()
                maven("https://maven.aliyun.com/repository/public/")
                maven("https://maven.aliyun.com/repository/google/")
                maven("https://maven.aliyun.com/repository/gradle-plugin/")
                maven("https://jitpack.io/")
                google()
                mavenCentral()
                gradlePluginPortal()
            }
            resolutionStrategy {
                eachPlugin {
                    println("#>> plugin: ${requested.id}")
                    if (requested.id.toString() == EzGradleConstants.PROJECT_PLUGIN_ID) {
                        useModule(EzGradleConstants.PROJECT_PLUGIN_COORDINATE)
                    }
                }
            }
        }
        dependencyResolutionManagement {
            repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
            repositories.addAll(pluginManagement.repositories)
            versionCatalogs {
                create("ezLibs") {
                    from(EzGradleConstants.CATALOG_COORDINATE)
                }
            }
        }
    }
}