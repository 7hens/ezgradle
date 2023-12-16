package me.thens.ezgradle

import org.gradle.api.artifacts.dsl.DependencyConstraintHandler
import org.gradle.api.artifacts.dsl.DependencyHandler
import java.io.File

fun DependencyHandler.implementation(notation: Any) =
    add("implementation", notation)

fun DependencyHandler.debugImplementation(notation: Any) =
    add("debugImplementation", notation)

fun DependencyHandler.testImplementation(notation: Any) =
    add("testImplementation", notation)

fun DependencyHandler.androidTestImplementation(notation: Any) =
    add("androidTestImplementation", notation)

fun DependencyHandler.kapt(notation: Any) = add("kapt", notation)
fun DependencyHandler.bom(notion: Any) {
    val platform = platform(notion)
    implementation(platform)
    androidTestImplementation(platform)
}

fun DependencyConstraintHandler.add(notion: Any) {
    add("api", notion)
}

fun DependencyHandler.resolveVersions(file: File) {
    if (!file.isFile) {
        return
    }
    constraints {
        file.bufferedReader().use { reader ->
            reader.lineSequence()
                .filter { it.isNotBlank() }
                .forEach { add(it) }
        }
    }
}

fun DependencyHandler.platform() {
    bom("androidx.compose:compose-bom:2023.08.00")
    bom("io.github.jan-tennert.supabase:bom:1.4.7")
    constraints {
        add("androidx.core:core-ktx:1.10.1")
        add("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
        add("androidx.activity:activity-compose:1.8.0")
        add("com.orhanobut:logger:2.2.0")
        add("io.ktor:ktor-client-android:2.3.6")
        add("junit:junit:4.13.2")
        add("androidx.test.ext:junit:1.1.5")
        add("androidx.test.espresso:espresso-core:3.5.1")
        val hiltVersion = "2.47"
        add("com.google.dagger:hilt-android:$hiltVersion")
        add("com.google.dagger:hilt-compiler:$hiltVersion")
        add("androidx.hilt:hilt-navigation-compose:1.1.0")
        add("junit:junit:4.13.2")
        add("androidx.test.ext:junit:1.1.5")
        add("androidx.test.espresso:espresso-core:3.5.1")
    }
}

fun DependencyHandler.builtInLibs(block: BuiltInLibs.()-> Unit) {
    block(BuiltInLibs(this))
}

class BuiltInLibs(dependencies: DependencyHandler): DependencyHandler by dependencies {
    fun compose() {
        implementation("androidx.activity:activity-compose")
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
    }

    fun hilt() {
        implementation("com.google.dagger:hilt-android")
        kapt("com.google.dagger:hilt-compiler")
//    implementation("androidx.hilt:hilt-navigation-compose")
    }

    fun test() {
        testImplementation("junit:junit")
        androidTestImplementation("androidx.test.ext:junit")
        androidTestImplementation("androidx.test.espresso:espresso-core")
    }
}