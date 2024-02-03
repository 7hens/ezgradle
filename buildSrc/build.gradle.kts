plugins {
    `kotlin-dsl`
    kotlin("plugin.serialization") version "1.9.20"
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    testImplementation("junit:junit:4.13.2")
}
