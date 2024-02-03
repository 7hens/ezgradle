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

dependencies {
    fun plugin(id: String) = "$id:$id.gradle.plugin"
    fun kotlin(name: String) = "org.jetbrains.kotlin.$name"

    implementation(platform(project(":ezgradle-bom")))
    implementation(plugin("com.android.application"))
    implementation(plugin("com.android.library"))
    implementation(plugin(kotlin("jvm")))
    implementation(plugin(kotlin("android")))
    implementation(plugin(kotlin("plugin.serialization")))
    implementation(plugin(kotlin("plugin.parcelize")))
    implementation(plugin("com.google.dagger.hilt.android"))
    implementation(plugin("com.google.devtools.ksp"))
    implementation(gradleApi())
    implementation(localGroovy())

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")
    testImplementation("junit:junit")
}

GenerateBuildConfigTask.register(project)
