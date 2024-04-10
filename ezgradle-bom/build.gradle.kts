plugins {
    id("maven-publish")
    id("java-platform")
}

javaPlatform {
    allowDependencies()
}

fun Provider<PluginDependency>.plugin(): Provider<String> {
    return map { it.run { "$pluginId:$pluginId.gradle.plugin:$version" } }
}

dependencies {
//#LIBS_START
    api(platform(libs.kotlin.bom))
    api(platform(libs.asm.bom))
    api(platform(libs.supabase.bom))
    api(platform(libs.coil.bom))
    api(platform(libs.koin.bom))
    api(platform(libs.okhttp.bom))
    api(platform(libs.firebase.bom))
    api(platform(libs.jackson.bom))
    api(platform(libs.compose.bom))
    api(platform(libs.mockito.bom))
    api(platform(libs.junit.bom))
    constraints {
        api(libs.robolectric.android)
        api(libs.appcompat)
        api(libs.core.ktx)
        api(libs.appcompat.resources)
        api(libs.tomlj)
        api(libs.work.multiprocess3)
        api(libs.work.testing3)
        api(libs.work.gcm3)
        api(libs.work.runtime.ktx3)
        api(libs.window.extensions.core)
        api(libs.window.testing)
        api(libs.window)
        api(libs.webkit)
        api(libs.viewpager2)
        api(libs.transition.ktx)
        api(libs.tracing.ktx)
        api(libs.uiautomator)
        api(libs.test.truth)
        api(libs.test.junit)
        api(libs.test.junit.ktx)
        api(libs.test.espresso.core)
        api(libs.test.orchestrator)
        api(libs.test.core.ktx)
        api(libs.startup.runtime)
        api(libs.room.paging)
        api(libs.room.testing)
        api(libs.room.guava)
        api(libs.room.compiler)
        api(libs.room.runtime)
        api(libs.profileinstaller)
        api(libs.preference.ktx)
        api(libs.paging.compose)
        api(libs.paging.common)
        api(libs.paging.runtime)
        api(libs.metrics.performance)
        api(libs.media3.common)
        api(libs.media3.datasource)
        api(libs.media3.decoder)
        api(libs.media3.database)
        api(libs.media3.test.utils.robolectric)
        api(libs.media3.test.utils)
        api(libs.media3.transformer)
        api(libs.media3.exoplayer.workmanager)
        api(libs.media3.cast)
        api(libs.media3.extractor)
        api(libs.media3.session)
        api(libs.media3.ui.leanback)
        api(libs.media3.ui)
        api(libs.media3.datasource.rtmp)
        api(libs.media3.datasource.okhttp)
        api(libs.media3.datasource.cronet)
        api(libs.media3.exoplayer.ima)
        api(libs.media3.exoplayer.rtsp)
        api(libs.media3.exoplayer.hls)
        api(libs.media3.exoplayer.dash)
        api(libs.media3.exoplayer)
        api(libs.navigation.compose)
        api(libs.lifecycle.viewmodel.savedstate)
        api(libs.lifecycle.viewmodel.ktx)
        api(libs.lifecycle.viewmodel.ktx2)
        api(libs.lifecycle.viewmodel.compose)
        api(libs.lifecycle.service)
        api(libs.lifecycle.runtime.ktx)
        api(libs.lifecycle.runtime.testing)
        api(libs.lifecycle.runtime.compose)
        api(libs.lifecycle.reactivestreams.ktx)
        api(libs.lifecycle.process)
        api(libs.lifecycle.livedata.ktx)
        api(libs.lifecycle.compiler)
        api(libs.hilt.compiler)
        api(libs.hilt.work)
        api(libs.hilt.navigation.compose)
        api(libs.heifwriter)
        api(libs.exifinterface)
        api(libs.emoji2.views.helper)
        api(libs.annotation)
        api(libs.ads.identifier)
        api(libs.activity.compose)
        api(libs.activity.ktx)
        api(libs.annotation.experimental)
        api(libs.kotlinx.serialization.flf)
        api(libs.kotlinx.serialization.csv)
        api(libs.commons.logging)
        api(libs.commons.codec)
        api(libs.commons.io)
        api(libs.retrofit2.mock)
        api(libs.retrofit2)
        api(libs.retrofit2.converter.protobuf2)
        api(libs.retrofit2.converter.jackson2)
        api(libs.retrofit2.converter.gson2)
        api(libs.retrofit2.adapter.rxjava3)
        api(libs.leakcanary.android)
        api(libs.kotlinpoet)
        api(libs.javapoet)
        api(libs.timber)
        api(libs.retrofit2.kotlinx.serialization.converter)
        api(libs.zxing.android.integration)
        api(libs.zxing.android.core)
        api(libs.maps.ktx)
        api(libs.maps.compose)
        api(libs.ksp.symbol.processing.api)
        api(libs.dagger.hilt.android.testing)
        api(libs.dagger.hilt.compiler)
        api(libs.dagger.hilt.android.compiler)
        api(libs.dagger.hilt.android)
        api(libs.gson)
        api(libs.material)
        api(libs.places)
        api(libs.play.services.maps)
        api(libs.systemuicontroller)
        api(libs.permissions)
        api(libs.drawablepainter)
        api(libs.glide)
        api(libs.fastjson)
        api(libs.fastjson2)
        api(libs.ktoml.file)
        api(libs.ktoml.core)
        api(libs.lottie)
        api(libs.acra.toast)
        api(libs.acra.notification)
        api(libs.acra.mail)
        api(libs.acra.limiter)
        api(libs.acra.http)
        api(libs.acra.dialog)
        api(libs.acra.core)
        api(libs.acra.advanced.scheduler)
        api(libs.emoji2.views)
        api(libs.emoji2)
        api(libs.dynamicanimation.ktx)
        api(libs.dynamicanimation)
        api(libs.datastore)
        api(libs.datastore.preferences)
        api(libs.credentials)
        api(libs.core.splashscreen)
        api(libs.core.remoteviews)
        api(libs.core.google.shortcuts)
        api(libs.core.performance)
        api(libs.core.animation.testing)
        api(libs.core.animation)
        api(libs.core.role)
        api(libs.constraintlayout.compose)
        api(libs.constraintlayout)
        api(libs.camera.extensions)
        api(libs.camera.view)
        api(libs.camera.video)
        api(libs.camera.lifecycle)
        api(libs.camera.camera2)
        api(libs.camera.core)
        api(libs.bluetooth)
        api(libs.biometric)
        api(libs.benchmark.macro.junit4)
        api(libs.autofill)
        api(libs.core.testing)
        api(libs.jsoup)
        api(libs.joor.java8)
        api(libs.joor)
        api(libs.jool)
        api(libs.kotlinx.serialization.properties)
        api(libs.kotlinx.serialization.cbor)
        api(libs.kotlinx.serialization.protobuf)
        api(libs.kotlinx.serialization.json.okio)
        api(libs.kotlinx.serialization.json)
        api(libs.kotlinx.io.core)
        api(libs.kotlinx.datetime)
        api(libs.kotlinx.coroutines.android)
        api(libs.kotlinx.coroutines.core)
        api(libs.kotlinx.collections.immutable)
        api(libs.javassist)
        api(libs.aspectj.rt)
        api(libs.aspectj.weaver)
        api(libs.commons.collections4)
        api(libs.commons.lang3)
        api(libs.yamlkt)
        api(libs.junit)
        api(libs.rxjava3.rxandroid2)
        api(libs.rxjava3)
        api(libs.ktor.client.android)
        api(libs.xmlutil.serialization)
        api(libs.sandbox)
        api(libs.robolectric)
        api(libs.voyager.hilt)
        api(libs.voyager.transitions)
        api(libs.voyager.tab.navigator)
        api(libs.voyager.screenmodel)
        api(libs.voyager.navigator)
        api(libs.plugins.benchmark.plugin())
        api(libs.plugins.android.application.plugin())
        api(libs.plugins.android.library.plugin())
        api(libs.plugins.kotlin.jvm.plugin())
        api(libs.plugins.kotlin.android.plugin())
        api(libs.plugins.kotlin.serialization.plugin())
        api(libs.plugins.kotlin.parcelize.plugin())
        api(libs.plugins.secretes.plugin())
        api(libs.plugins.ksp.plugin())
        api(libs.plugins.dagger.hilt.android.plugin())
        api(libs.plugins.ezgradle.plugin())
    }
//#LIBS_END
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            version = project.version.toString()
            from(components.getByName("javaPlatform"))
        }
    }
}
