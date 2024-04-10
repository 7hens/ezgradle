package me.thens.ezgradle.config

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import me.thens.ezgradle.model.EzGradleProperties
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

internal fun Project.configureAndroidApplication(properties: EzGradleProperties) {
    androidApplication {
        configureAndroidCommon(this, properties)
        defaultConfig {
            val version = project.version.toString()
            applicationId = namespace
            targetSdk = properties.libs.versions.androidTargetSdk.toInt()
            versionCode = version.toVersionCode()
            versionName = version
        }
    }
}

internal fun Project.configureAndroidLibrary(properties: EzGradleProperties) {
    androidLibrary {
        configureAndroidCommon(this, properties)
        defaultConfig {
            consumerProguardFiles("consumer-rules.pro")
        }
    }
}

private fun Project.configureAndroidCommon(
    android: AndroidCommonExtension,
    properties: EzGradleProperties
) {
    android.apply {
        namespace = getDefaultPackageName()
        compileSdk = properties.libs.versions.androidTargetSdk.toInt()
        defaultConfig {
            minSdk = properties.libs.versions.androidMinSdk.toInt()
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
            BuildConfigGenerator(project).getProperties().entries.forEach { (key, value) ->
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
            kotlinCompilerExtensionVersion = properties.libs.versions.composeCompiler
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}

