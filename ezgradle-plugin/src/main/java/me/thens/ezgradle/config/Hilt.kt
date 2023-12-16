package me.thens.ezgradle.config

import dagger.hilt.android.plugin.HiltExtension
import me.thens.ezgradle.misc.configure
import org.gradle.api.Project

internal fun Project.configureHilt() {
    if (plugins.hasPlugin("com.google.dagger.hilt.android")) {
        configure<HiltExtension>("hilt") {
            enableAggregatingTask = true
        }
    }
}
