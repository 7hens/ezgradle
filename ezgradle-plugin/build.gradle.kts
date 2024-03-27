import me.thens.ezgradle.util.GenerateBuildConfigTask

plugins {
    `kotlin-dsl`
    id("java-gradle-plugin")
    id("maven-publish")
}

sourceSets {
    get("main").apply {
        kotlin.srcDirs("build/generated/src/main/kotlin")
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
    implementation(libs.plugins.android.application.plugin())
    implementation(libs.plugins.android.library.plugin())
    implementation(libs.plugins.kotlin.jvm.plugin())
    implementation(libs.plugins.kotlin.android.plugin())
    implementation(libs.plugins.kotlin.serialization.plugin())
//    implementation(libs.plugins.kotlin.parcelize.plugin())
    implementation(libs.plugins.dagger.hilt.android.plugin())
    implementation(libs.plugins.ksp.plugin())
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.junit)
}

GenerateBuildConfigTask.register(project)
