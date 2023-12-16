package me.thens.ezgradle.config

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import me.thens.ezgradle.misc.configure
import me.thens.ezgradle.misc.extra
import me.thens.ezgradle.misc.versionNameToCode
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

internal fun Project.configureAndroidApplication() {
    if (plugins.hasPlugin("com.android.application")) {
        configure<ApplicationExtension>("android") {
            configureAndroidCommon(this)
            defaultConfig {
                val version = extra("VERSION")
                applicationId = namespace
                targetSdk = compileSdk
                versionCode = extra("VERSION_CODE").toIntOrNull() ?: versionNameToCode(version)
                versionName = version
            }
        }
    }
}

internal fun Project.configureAndroidLibrary() {
    if (plugins.hasPlugin("com.android.library")) {
        configure<LibraryExtension>("android") {
            configureAndroidCommon(this)
            defaultConfig {
                consumerProguardFiles("consumer-rules.pro")
            }
        }
    }
}

private fun Project.configureAndroidCommon(android: CommonExtension<*, *, *, *, *>) {
    android.apply {
        namespace = extra("PACKAGE_NAME")
        compileSdk = 34
        defaultConfig {
            minSdk = 21
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }

            extra.properties.entries
                .filter { it.key.matches(Regex("[A-Z_]+")) }
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
            compose = true
            buildConfig = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.4.3"
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}

