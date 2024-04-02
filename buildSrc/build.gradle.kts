plugins {
    `kotlin-dsl`
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
//    implementation("com.didiglobal.booster:booster-api:4.16.3")
    implementation(libs.junit)
}
