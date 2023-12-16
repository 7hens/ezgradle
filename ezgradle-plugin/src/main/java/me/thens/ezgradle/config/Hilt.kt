package me.thens.ezgradle.config

import dagger.hilt.android.plugin.HiltExtension
import me.thens.ezgradle.misc.ext
import org.gradle.api.Project

internal fun Project.configureHilt() {
    if (plugins.hasPlugin("com.google.dagger.hilt.android")) {
        ext<HiltExtension>("hilt") {
            enableAggregatingTask = true
        }
    }
}
