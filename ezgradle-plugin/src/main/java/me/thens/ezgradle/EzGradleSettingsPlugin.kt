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

    private fun Settings.configure() {
        pluginManagement {
            resolutionStrategy {
                eachPlugin {
                    println("#>> plugin: ${requested.id}")
                    if (requested.id.toString() == EzGradleConstants.PROJECT_GROUP) {
                        useModule(EzGradleConstants.SETTINGS_PLUGIN_COORDINATE)
                    }
                }
            }
        }
        dependencyResolutionManagement {
            versionCatalogs {
                create("ezLibs") {
                    from(EzGradleConstants.CATALOG_COORDINATE)
                }
            }
        }
    }

    @Suppress("UnstableApiUsage")
    private fun Settings.addRepositories() {
        pluginManagement {
            repositories {
                mavenLocal()
                maven("https://maven.aliyun.com/repository/public/")
                maven("https://maven.aliyun.com/repository/google/")
                maven("https://maven.aliyun.com/repository/gradle-plugin/")
                maven("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
                maven("https://jitpack.io/")
                google()
                mavenCentral()
                gradlePluginPortal()
            }
        }
        dependencyResolutionManagement {
            repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
            repositories.addAll(pluginManagement.repositories)
        }
    }
}