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
    implementation(libs.plugins.comAndroidApplication.plugin())
    implementation(libs.plugins.comAndroidLibrary.plugin())
    implementation(libs.plugins.orgJetbrainsKotlinJvm.plugin())
    implementation(libs.plugins.orgJetbrainsKotlinAndroid.plugin())
    implementation(libs.plugins.orgJetbrainsKotlinPluginSerialization.plugin())
//    implementation(libs.plugins.kotlin.parcelize.plugin())
    implementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesCore)
    implementation(libs.orgJetbrainsKotlinx.kotlinxSerializationJson)
    implementation(libs.orgTomlj.tomlj)
    testImplementation(libs.junit.junit)
}

tasks.getByName<Test>("test") {
    testLogging {
        showStandardStreams = true
//        events("standardOut", "passed", "failed")
        events("standardOut")
    }
}