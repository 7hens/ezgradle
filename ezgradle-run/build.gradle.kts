plugins {
    `kotlin-dsl`
}

fun Provider<PluginDependency>.plugin(): Provider<String> {
    return map { it.run { "$pluginId:$pluginId.gradle.plugin:$version" } }
}

dependencies {
//    fun plugin(id: String) = "$id:$id.gradle.plugin"
//    fun kotlin(name: String) = "org.jetbrains.kotlin.$name"

    implementation(gradleApi())
    implementation(localGroovy())
    implementation(project(":com.github.7hens.ezgradle.gradle.plugin"))
    implementation(libs.plugins.android.application.plugin())
    implementation(libs.plugins.android.library.plugin())
    implementation(libs.plugins.kotlin.jvm.plugin())
    implementation(libs.plugins.kotlin.android.plugin())
    implementation(libs.plugins.kotlin.serialization.plugin())
//    implementation(libs.plugins.kotlin.parcelize.plugin())
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.tomlj)
    testImplementation(libs.junit)
}

tasks.getByName<Test>("test") {
    testLogging {
        showStandardStreams = true
//        events("standardOut", "passed", "failed")
        events("standardOut")
    }
}