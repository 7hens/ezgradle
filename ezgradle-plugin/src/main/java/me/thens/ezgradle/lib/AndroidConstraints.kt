package me.thens.ezgradle.lib

import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.applyAndroidConstraints() {
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    constraints {
        implementation("androidx.activity:activity-ktx:1.8.2")
        implementation("androidx.activity:activity-compose:1.8.0")
        implementation("androidx.ads:ads-identifier:1.0.0-alpha05")
        implementation("androidx.annotation:annotation:1.7.1")
        implementation("androidx.annotation:annotation-experimental:1.3.1")
        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("androidx.appcompat:appcompat-resources:1.6.1")
        val archVersion = "2.2.0"
        testImplementation("androidx.arch.core:core-testing:$archVersion")
        implementation("androidx.biometric:biometric:1.1.0")
        implementation("androidx.bluetooth:bluetooth:1.0.0-alpha02")
        val cameraXVersion = "1.4.0-alpha03"
        implementation("androidx.camera:camera-core:$cameraXVersion")
        implementation("androidx.camera:camera-camera2:$cameraXVersion")
        implementation("androidx.camera:camera-lifecycle:$cameraXVersion")
        implementation("androidx.camera:camera-video:$cameraXVersion")
        implementation("androidx.camera:camera-view:$cameraXVersion")
        implementation("androidx.camera:camera-mlkit-vision:$cameraXVersion")
        implementation("androidx.camera:camera-extensions:$cameraXVersion")
        implementation("androidx.core:core-ktx:1.10.1")
        val emoji2Version = "1.4.0"
        implementation("androidx.emoji2:emoji2:$emoji2Version")
        implementation("androidx.emoji2:emoji2-views:$emoji2Version")
        implementation("androidx.emoji2:emoji2-views-helper:$emoji2Version")
        implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
        val lifecycleVersion = "2.6.2"
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")
        kapt("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-service:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-process:$lifecycleVersion")
        implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycleVersion")
        testImplementation ("androidx.lifecycle:lifecycle-runtime-testing:$lifecycleVersion")
        val roomVersion = "2.6.1"
        implementation("androidx.room:room-runtime:$roomVersion")
        annotationProcessor("androidx.room:room-compiler:$roomVersion")
        kapt("androidx.room:room-compiler:$roomVersion")
        ksp("androidx.room:room-compiler:$roomVersion")
        implementation("androidx.room:room-rxjava2:$roomVersion")
        implementation("androidx.room:room-rxjava3:$roomVersion")
        implementation("androidx.room:room-guava:$roomVersion")
        testImplementation("androidx.room:room-testing:$roomVersion")
        implementation("androidx.room:room-paging:$roomVersion")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        implementation("com.google.android.material:material:1.10.0")
        val hiltVersion = "2.47"
        implementation("com.google.dagger:hilt-android:$hiltVersion")
        implementation("com.google.dagger:hilt-compiler:$hiltVersion")
        implementation("io.ktor:ktor-client-android:2.3.6")
        testImplementation("junit:junit:4.13.2")
    }
}

