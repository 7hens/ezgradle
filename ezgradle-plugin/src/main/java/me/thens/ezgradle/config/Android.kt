package me.thens.ezgradle.config

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.github.a7hens.ezgradle.com.github.a7hens.ezgradle.gradle.plugin.BuildConfig
import me.thens.ezgradle.util.GenerateBuildConfigTask
import me.thens.ezgradle.util.configure
import me.thens.ezgradle.util.getDefaultPackageName
import me.thens.ezgradle.util.toVersionCode
import org.gradle.api.Project

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
        namespace = getDefaultPackageName()
        compileSdk = 34
        defaultConfig {
            minSdk = 21
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
            GenerateBuildConfigTask.getProperties(project).entries.forEach { (key, value) ->
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
            kotlinCompilerExtensionVersion = "1.5.5"
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}

