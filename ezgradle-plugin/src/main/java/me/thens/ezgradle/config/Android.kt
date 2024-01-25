package me.thens.ezgradle.config

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import me.thens.ezgradle.ProjectConfig
import me.thens.ezgradle.misc.configure
import me.thens.ezgradle.misc.toPackageName
import me.thens.ezgradle.misc.toVersionCode
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

typealias AndroidCommonExtension = CommonExtension<*, *, *, *, *>

internal fun Project.androidCommon(block: AndroidCommonExtension.() -> Unit) {
    androidLibrary {
        androidCommon(block)
    }
    androidApplication {
        androidCommon(block)
    }
}

internal fun Project.androidLibrary(block: LibraryExtension.() -> Unit) {
    if (plugins.hasPlugin("com.android.library")) {
        configure("android", block)
    }
}

internal fun Project.androidApplication(block: ApplicationExtension.() -> Unit) {
    if (plugins.hasPlugin("com.android.application")) {
        configure("android", block)
    }
}

internal fun Project.configureAndroidApplication() {
    androidApplication {
        configureAndroidCommon(this)
        defaultConfig {
            val version = project.version.toString()
            applicationId = namespace
            targetSdk = compileSdk
            versionCode = version.toVersionCode()
            versionName = version
        }
    }
}

internal fun Project.configureAndroidLibrary() {
    androidLibrary {
        configureAndroidCommon(this)
        defaultConfig {
            consumerProguardFiles("consumer-rules.pro")
        }
    }
}

private fun Project.configureAndroidCommon(android: AndroidCommonExtension) {
    android.apply {
        namespace = "${project.group}.${project.name}".toPackageName()
        compileSdk = 34
        defaultConfig {
            minSdk = 21
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }

            val propPrefix = "project."
            extra.properties.entries
                .filter { it.key.startsWith(propPrefix) }
                .map { it.key.substringAfter(propPrefix).uppercase() to it.value }
                .forEach { (key, value) ->
                    buildConfigField("String", key, "\"$value\"")
                    manifestPlaceholders[key] = value
                }
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        buildFeatures {
            compose = false
            buildConfig = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = ProjectConfig.COMPOSE_COMPILER_VERSION
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}

