plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    mavenCentral()
    google()
    maven("https://jitpack.io")
}

gradlePlugin {
    plugins {
        fun register(pluginId: String, className: String) {
            register(pluginId) {
                id = pluginId
                implementationClass = className
            }
        }
        register("me.thens.ezgradle", "me.thens.ezgradle.EzGradlePlugin")
    }
}

dependencies {
    fun plugin(pluginId: String, version: String) = "$pluginId:$pluginId.gradle.plugin:$version"
    fun kotlin(name: String) = "org.jetbrains.kotlin.$name"

    val agpVersion = "8.2.0"
    implementation(plugin("com.android.application", agpVersion))
    implementation(plugin("com.android.library", agpVersion))
    val kotlinVersion = "1.8.10"
    implementation(plugin(kotlin("jvm"), kotlinVersion))
    implementation(plugin(kotlin("android"), kotlinVersion))
    implementation(plugin(kotlin("plugin.serialization"), kotlinVersion))
    implementation(plugin(kotlin("plugin.parcelize"), kotlinVersion))
    implementation(plugin("com.google.dagger.hilt.android", "2.47"))
//    implementation(kotlin("gradle-plugin"))
    implementation(gradleApi())
    implementation(localGroovy())
}
