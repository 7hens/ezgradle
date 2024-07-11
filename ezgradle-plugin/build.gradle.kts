import org.w3c.dom.Element

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
        create<MavenPublication>("settingsPlugin") {
            version = project.version.toString()
            from(components.getByName("java"))
        }
        create<MavenPublication>("projectPlugin") {
            groupId = "${project.group}.project"
            artifactId = "$groupId.gradle.plugin"
            version = project.version.toString()
//            from(components.getByName("java"))
            pom.withXml {
                val root: Element = asElement()
                val document = root.ownerDocument
                val dependencies = root.appendChild(document.createElement("dependencies"))
                val dependency = dependencies.appendChild(document.createElement("dependency"))
                mapOf(
                    "groupId" to "${project.group}",
                    "artifactId" to project.name,
                    "version" to version,
                    "scope" to "runtime",
                ).forEach { (key, value) ->
                    dependency.appendChild(document.createElement(key)).textContent = value
                }
            }
        }
    }
}

gradlePlugin {
    isAutomatedPublishing = false
    plugins {
        register("ezgradle") {
            id = "${project.group}"
            implementationClass = "me.thens.ezgradle.EzGradleSettingsPlugin"
        }
        register("ezgradleProject") {
            id = "${project.group}.project"
            implementationClass = "me.thens.ezgradle.EzGradleProjectPlugin"
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
