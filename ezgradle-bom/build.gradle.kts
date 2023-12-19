plugins {
    id("maven-publish")
    id("java-platform")
}

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform("androidx.compose:compose-bom:2023.10.01"))
    api(platform("io.github.jan-tennert.supabase:bom:1.4.7"))
    api(platform("org.junit:junit-bom:5.10.1"))
    api(platform("org.mockito:mockito-bom:5.8.0"))
    constraints {
        fun plugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"
        fun kotlin(name: String) = "org.jetbrains.kotlin.$name"
        val agpVersion = "8.2.0"
        api(plugin("com.android.application", agpVersion))
        api(plugin("com.android.library", agpVersion))
        val kotlinVersion = "1.8.10"
        api(plugin(kotlin("jvm"), kotlinVersion))
        api(plugin(kotlin("android"), kotlinVersion))
        api(plugin(kotlin("plugin.serialization"), kotlinVersion))
        api(plugin(kotlin("plugin.parcelize"), kotlinVersion))
        val hiltVersion = "2.47"
        api(plugin("com.google.dagger.hilt.android", hiltVersion))

        api("androidx.activity:activity-ktx:1.8.2")
        api("androidx.activity:activity-compose:1.8.0")
        api("androidx.ads:ads-identifier:1.0.0-alpha05")
        api("androidx.annotation:annotation:1.7.1")
        api("androidx.annotation:annotation-experimental:1.3.1")
        api("androidx.appcompat:appcompat:1.6.1")
        api("androidx.appcompat:appcompat-resources:1.6.1")
        val archVersion = "2.2.0"
        api("androidx.arch.core:core-testing:$archVersion")
        api("androidx.biometric:biometric:1.1.0")
        api("androidx.bluetooth:bluetooth:1.0.0-alpha02")
        val cameraXVersion = "1.4.0-alpha03"
        api("androidx.camera:camera-core:$cameraXVersion")
        api("androidx.camera:camera-camera2:$cameraXVersion")
        api("androidx.camera:camera-lifecycle:$cameraXVersion")
        api("androidx.camera:camera-video:$cameraXVersion")
        api("androidx.camera:camera-view:$cameraXVersion")
        api("androidx.camera:camera-mlkit-vision:$cameraXVersion")
        api("androidx.camera:camera-extensions:$cameraXVersion")
        api("androidx.core:core-ktx:1.10.1")
        val emoji2Version = "1.4.0"
        api("androidx.emoji2:emoji2:$emoji2Version")
        api("androidx.emoji2:emoji2-views:$emoji2Version")
        api("androidx.emoji2:emoji2-views-helper:$emoji2Version")
        api("androidx.hilt:hilt-navigation-compose:1.1.0")
        api("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
        val lifecycleVersion = "2.6.2"
        api("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
        api("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
        api("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
        api("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
        api("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
        api("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")
        api("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")
        api("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")
        api("androidx.lifecycle:lifecycle-service:$lifecycleVersion")
        api("androidx.lifecycle:lifecycle-process:$lifecycleVersion")
        api("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycleVersion")
        api("androidx.lifecycle:lifecycle-runtime-testing:$lifecycleVersion")
        api("androidx.navigation:navigation-compose:2.6.0")
        val media3Version = "1.1.0"
        api("androidx.media3:media3-exoplayer:$media3Version")
        api("androidx.media3:media3-ui:$media3Version")
        val roomVersion = "2.6.1"
        api("androidx.room:room-runtime:$roomVersion")
        api("androidx.room:room-compiler:$roomVersion")
        api("androidx.room:room-rxjava2:$roomVersion")
        api("androidx.room:room-rxjava3:$roomVersion")
        api("androidx.room:room-guava:$roomVersion")
        api("androidx.room:room-testing:$roomVersion")
        api("androidx.room:room-paging:$roomVersion")
        api("androidx.test.ext:junit:1.1.5")
        api("androidx.test.espresso:espresso-core:3.5.1")
        val accompanistVersion = "0.33.0-alpha"
        api("com.google.accompanist:accompanist-drawablepainter:$accompanistVersion")
        api("com.google.accompanist:accompanist-permissions:$accompanistVersion")
        api("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
        api("com.google.android.material:material:1.10.0")
        api("com.google.code.gson:gson:2.10.1")
        api("com.google.dagger:hilt-android:$hiltVersion")
        api("com.google.dagger:hilt-android-compiler:$hiltVersion")
        api("com.google.dagger:hilt-compiler:$hiltVersion")
        api("com.google.dagger:hilt-android-testing:$hiltVersion")
        api("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
        api("com.squareup:javapoet:1.13.0")
        api("com.squareup:kotlinpoet:1.15.3")
        api("com.squareup.okhttp3:logging-interceptor:4.11.0")
        api("com.squareup.retrofit2:retrofit:2.9.0")
        api("commons-io:commons-io:2.15.1")
        api("commons-codec:commons-codec:1.16.0")
        api("commons-logging:commons-logging:1.3.0")
        val coilVersion = "2.4.0"
        api("io.coil-kt:coil-compose:$coilVersion")
        api("io.coil-kt:coil-video:$coilVersion")
        api("io.ktor:ktor-client-android:2.3.6")
        api("junit:junit:4.13.2")
        api("org-apache.commons:commons-lang3:3.14.0")
        api("org-apache.commons:commons-collections4:4.4")
        val aspectjVersion = "3.24.2"
        api("org.aspectj:aspect-core:$aspectjVersion")
        api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components.getByName("javaPlatform"))
        }
    }
}