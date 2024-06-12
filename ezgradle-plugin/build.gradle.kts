//import me.thens.ezgradle.util.GenerateBuildConfigTask

plugins {
    `kotlin-dsl`
    id("java-gradle-plugin")
    id("maven-publish")
}

sourceSets {
    get("main").apply {
        java.srcDirs("build/generated/source/ezgradle/main/java")
        kotlin.srcDirs("../buildSrc/src/main/kotlin")
    }
}

publishing {
    publications {
        create<MavenPublication>("pluginMaven") {
            version = project.version.toString()
            from(components.getByName("java"))
        }
    }
}

gradlePlugin {
    isAutomatedPublishing = false
    plugins {
        register("ezgradle") {
            id = "com.github.7hens.ezgradle"
            implementationClass = "me.thens.ezgradle.EzGradlePlugin"
        }
    }
}

fun Provider<PluginDependency>.plugin(): Provider<String> {
    return map { it.run { "$pluginId:$pluginId.gradle.plugin:$version" } }
}

dependencies {
//    fun plugin(id: String) = "$id:$id.gradle.plugin"
//    fun kotlin(name: String) = "org.jetbrains.kotlin.$name"

    implementation(gradleApi())
    implementation(localGroovy())
    implementation(libs.plugins.comAndroidApplication.plugin())
    implementation(libs.plugins.comAndroidLibrary.plugin())
    implementation(libs.plugins.orgJetbrainsKotlinJvm.plugin())
    implementation(libs.plugins.orgJetbrainsKotlinAndroid.plugin())
    implementation(libs.plugins.orgJetbrainsKotlinPluginSerialization.plugin())
//    implementation(libs.plugins.kotlin.parcelize.plugin())
    implementation(libs.plugins.comGoogleDaggerHiltAndroid.plugin())
    implementation(libs.plugins.comGoogleDevtoolsKsp.plugin())
    implementation(libs.orgJetbrainsKotlinx.kotlinxCoroutinesCore)
    implementation(libs.orgJetbrainsKotlinx.kotlinxSerializationJson)
    testImplementation(libs.junit.junit)
}

//GenerateBuildConfigTask.register(project)
