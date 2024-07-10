package me.thens.ezgradle

object EzGradleConstants {
    const val GROUP = "com.gitlab.7hens.ezgradle"
    const val VERSION = "2024.03.01.2-SNAPSHOT"
    const val PROJECT_PLUGIN_ID = "$GROUP.project"
    const val SETTINGS_PLUGIN_COORDINATE = "$GROUP:$GROUP.gradle.plugin:$VERSION"
    const val PROJECT_PLUGIN_COORDINATE = "$GROUP:$GROUP.gradle.plugin.project:$VERSION"
    const val CATALOG_COORDINATE = "$GROUP:ezgradle-catalog:$VERSION"
    const val ANDROID_MIN_SDK = "21"
    const val ANDROID_TARGET_SDK = "34"
    const val COMPOSE_COMPILER = "1.5.8"
}