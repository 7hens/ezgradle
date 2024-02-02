plugins {
    `kotlin-dsl`
//    kotlin("jvm") version "1.9.20"
    kotlin("plugin.serialization") version "1.9.20"
}

dependencies {
//    implementation(kotlin("gradle-plugin"))
//    implementation(kotlin("serialization"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")
}
