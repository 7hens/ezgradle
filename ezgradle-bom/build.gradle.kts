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
    api(platform(libs.androidxCompose.composeBom))
    api(platform(libs.comFasterxmlJackson.jacksonBom))
    api(platform(libs.comGoogleFirebase.firebaseBom))
    api(platform(libs.comSquareupOkhttp3.okhttpBom))
    api(platform(libs.ioCoilKt.coilBom))
    api(platform(libs.ioGithubJanTennertSupabase.bom))
    api(platform(libs.ioInsertKoin.koinBom))
    api(platform(libs.orgJetbrainsKotlin.kotlinBom))
    api(platform(libs.orgJunit.junitBom))
    api(platform(libs.orgMockito.mockitoBom))
    api(platform(libs.orgOw2Asm.asmBom))
    constraints {
        api(libs.androidxActivity.activityCompose)
        api(libs.androidxActivity.activityKtx)
        api(libs.androidxAnnotation.annotation)
        api(libs.androidxAnnotation.annotationExperimental)
        api(libs.androidxAppcompat.appcompat)
        api(libs.androidxAppcompat.appcompatResources)
        api(libs.androidxArchCore.coreTesting)
        api(libs.androidxAutofill.autofill)
        api(libs.androidxBenchmark.benchmarkMacroJunit4)
        api(libs.androidxBiometric.biometric)
        api(libs.androidxBluetooth.bluetooth)
        api(libs.androidxCamera.cameraCamera2)
        api(libs.androidxCamera.cameraCore)
        api(libs.androidxCamera.cameraExtensions)
        api(libs.androidxCamera.cameraLifecycle)
        api(libs.androidxCamera.cameraVideo)
        api(libs.androidxCamera.cameraView)
        api(libs.androidxConstraintlayout.constraintlayout)
        api(libs.androidxConstraintlayout.constraintlayoutCompose)
        api(libs.androidxCore.coreAnimation)
        api(libs.androidxCore.coreAnimationTesting)
        api(libs.androidxCore.coreGoogleShortcuts)
        api(libs.androidxCore.coreKtx)
        api(libs.androidxCore.corePerformance)
        api(libs.androidxCore.coreRemoteviews)
        api(libs.androidxCore.coreRole)
        api(libs.androidxCore.coreSplashscreen)
        api(libs.androidxCredentials.credentials)
        api(libs.androidxDatastore.datastore)
        api(libs.androidxDatastore.datastorePreferences)
        api(libs.androidxDynamicanimation.dynamicanimation)
        api(libs.androidxDynamicanimation.dynamicanimationKtx)
        api(libs.androidxEmoji2.emoji2)
        api(libs.androidxEmoji2.emoji2Views)
        api(libs.androidxEmoji2.emoji2ViewsHelper)
        api(libs.androidxExifinterface.exifinterface)
        api(libs.androidxHeifwriter.heifwriter)
        api(libs.androidxHilt.hiltCompiler)
        api(libs.androidxHilt.hiltNavigationCompose)
        api(libs.androidxHilt.hiltWork)
        api(libs.androidxLifecycle.lifecycleCompiler)
        api(libs.androidxLifecycle.lifecycleLivedataKtx)
        api(libs.androidxLifecycle.lifecycleProcess)
        api(libs.androidxLifecycle.lifecycleReactivestreamsKtx)
        api(libs.androidxLifecycle.lifecycleRuntimeCompose)
        api(libs.androidxLifecycle.lifecycleRuntimeKtx)
        api(libs.androidxLifecycle.lifecycleRuntimeTesting)
        api(libs.androidxLifecycle.lifecycleService)
        api(libs.androidxLifecycle.lifecycleViewmodelCompose)
        api(libs.androidxLifecycle.lifecycleViewmodelKtx)
        api(libs.androidxLifecycle.lifecycleViewmodelSavedstate)
        api(libs.androidxMedia3.media3Cast)
        api(libs.androidxMedia3.media3Common)
        api(libs.androidxMedia3.media3Database)
        api(libs.androidxMedia3.media3Datasource)
        api(libs.androidxMedia3.media3DatasourceCronet)
        api(libs.androidxMedia3.media3DatasourceOkhttp)
        api(libs.androidxMedia3.media3DatasourceRtmp)
        api(libs.androidxMedia3.media3Decoder)
        api(libs.androidxMedia3.media3Exoplayer)
        api(libs.androidxMedia3.media3ExoplayerDash)
        api(libs.androidxMedia3.media3ExoplayerHls)
        api(libs.androidxMedia3.media3ExoplayerIma)
        api(libs.androidxMedia3.media3ExoplayerRtsp)
        api(libs.androidxMedia3.media3ExoplayerWorkmanager)
        api(libs.androidxMedia3.media3Extractor)
        api(libs.androidxMedia3.media3Session)
        api(libs.androidxMedia3.media3TestUtils)
        api(libs.androidxMedia3.media3TestUtilsRobolectric)
        api(libs.androidxMedia3.media3Transformer)
        api(libs.androidxMedia3.media3Ui)
        api(libs.androidxMedia3.media3UiLeanback)
        api(libs.androidxMetrics.metricsPerformance)
        api(libs.androidxNavigation.navigationCompose)
        api(libs.androidxPaging.pagingCommon)
        api(libs.androidxPaging.pagingCompose)
        api(libs.androidxPaging.pagingRuntime)
        api(libs.androidxPreference.preferenceKtx)
        api(libs.androidxProfileinstaller.profileinstaller)
        api(libs.androidxRoom.roomCompiler)
        api(libs.androidxRoom.roomGuava)
        api(libs.androidxRoom.roomPaging)
        api(libs.androidxRoom.roomRuntime)
        api(libs.androidxRoom.roomTesting)
        api(libs.androidxStartup.startupRuntime)
        api(libs.androidxTest.coreKtx)
        api(libs.androidxTest.orchestrator)
        api(libs.androidxTestEspresso.espressoCore)
        api(libs.androidxTestExt.junit)
        api(libs.androidxTestExt.junitKtx)
        api(libs.androidxTestExt.truth)
        api(libs.androidxTestUiautomator.uiautomator)
        api(libs.androidxTracing.tracingKtx)
        api(libs.androidxTransition.transitionKtx)
        api(libs.androidxViewpager2.viewpager2)
        api(libs.androidxWebkit.webkit)
        api(libs.androidxWindow.window)
        api(libs.androidxWindow.windowTesting)
        api(libs.androidxWindowExtensionsCore.core)
        api(libs.androidxWork.workGcm)
        api(libs.androidxWork.workMultiprocess)
        api(libs.androidxWork.workRuntimeKtx)
        api(libs.androidxWork.workTesting)
        api(libs.appSoftwork.kotlinxSerializationCsv)
        api(libs.appSoftwork.kotlinxSerializationFlf)
        api(libs.cafeAdrielVoyager.voyagerHilt)
        api(libs.cafeAdrielVoyager.voyagerNavigator)
        api(libs.cafeAdrielVoyager.voyagerScreenmodel)
        api(libs.cafeAdrielVoyager.voyagerTabNavigator)
        api(libs.cafeAdrielVoyager.voyagerTransitions)
        api(libs.chAcra.acraAdvancedScheduler)
        api(libs.chAcra.acraCore)
        api(libs.chAcra.acraDialog)
        api(libs.chAcra.acraHttp)
        api(libs.chAcra.acraLimiter)
        api(libs.chAcra.acraMail)
        api(libs.chAcra.acraNotification)
        api(libs.chAcra.acraToast)
        api(libs.comAirbnbAndroid.lottie)
        api(libs.comAkuleshov7.ktomlCore)
        api(libs.comAkuleshov7.ktomlFile)
        api(libs.comAlibaba.fastjson)
        api(libs.comAlibabaFastjson2.fastjson2)
        api(libs.comGithubBumptechGlide.glide)
        api(libs.comGoogleAccompanist.accompanistDrawablepainter)
        api(libs.comGoogleAccompanist.accompanistPermissions)
        api(libs.comGoogleAccompanist.accompanistSystemuicontroller)
        api(libs.comGoogleAndroidGms.playServicesMaps)
        api(libs.comGoogleAndroidLibrariesPlaces.places)
        api(libs.comGoogleAndroidMaterial.material)
        api(libs.comGoogleCodeGson.gson)
        api(libs.comGoogleDagger.hiltAndroid)
        api(libs.comGoogleDagger.hiltAndroidCompiler)
        api(libs.comGoogleDagger.hiltAndroidTesting)
        api(libs.comGoogleDagger.hiltCompiler)
        api(libs.comGoogleDevtoolsKsp.symbolProcessingApi)
        api(libs.comGoogleMapsAndroid.mapsCompose)
        api(libs.comGoogleMapsAndroid.mapsKtx)
        api(libs.comGoogleZxing.androidCore)
        api(libs.comGoogleZxing.androidIntegration)
        api(libs.comJakewhartonRetrofit.retrofit2KotlinxSerializationConverter)
        api(libs.comJakewhartonTimber.timber)
        api(libs.comSquareup.javapoet)
        api(libs.comSquareup.kotlinpoet)
        api(libs.comSquareupLeakcanary.leakcanaryAndroid)
        api(libs.comSquareupRetrofit2.adapterRxjava3)
        api(libs.comSquareupRetrofit2.converterGson)
        api(libs.comSquareupRetrofit2.converterJackson)
        api(libs.comSquareupRetrofit2.converterProtobuf)
        api(libs.comSquareupRetrofit2.retrofit)
        api(libs.comSquareupRetrofit2.retrofitMock)
        api(libs.commonsCodec.commonsCodec)
        api(libs.commonsIo.commonsIo)
        api(libs.commonsLogging.commonsLogging)
        api(libs.ioGithubPdvriezeXmlutil.serialization)
        api(libs.ioKtor.ktorClientAndroid)
        api(libs.ioReactivexRxjava3.rxandroid)
        api(libs.ioReactivexRxjava3.rxjava)
        api(libs.junit.junit)
        api(libs.netMamoeYamlkt.yamlkt)
        api(libs.orgApacheCommons.commonsCollections4)
        api(libs.orgApacheCommons.commonsLang3)
        api(libs.orgAspectj.aspectjrt)
        api(libs.orgAspectj.aspectjweaver)
        api(libs.orgJavassist.javassist)
        api(libs.orgJetbrainsKotlinx.kotlinxCollectionsImmutable)
        api(libs.orgJetbrainsKotlinx.kotlinxCoroutinesAndroid)
        api(libs.orgJetbrainsKotlinx.kotlinxCoroutinesCore)
        api(libs.orgJetbrainsKotlinx.kotlinxDatetime)
        api(libs.orgJetbrainsKotlinx.kotlinxIoCore)
        api(libs.orgJetbrainsKotlinx.kotlinxSerializationCbor)
        api(libs.orgJetbrainsKotlinx.kotlinxSerializationJson)
        api(libs.orgJetbrainsKotlinx.kotlinxSerializationJsonOkio)
        api(libs.orgJetbrainsKotlinx.kotlinxSerializationProperties)
        api(libs.orgJetbrainsKotlinx.kotlinxSerializationProtobuf)
        api(libs.orgJooq.jool)
        api(libs.orgJooq.joor)
        api(libs.orgJooq.joorJava8)
        api(libs.orgJsoup.jsoup)
        api(libs.orgRobolectric.androidAll)
        api(libs.orgRobolectric.robolectric)
        api(libs.orgRobolectric.sandbox)
        api(libs.orgTomlj.tomlj)
        api(libs.plugins.androidxBenchmark.plugin())
        api(libs.plugins.comAndroidApplication.plugin())
        api(libs.plugins.comAndroidLibrary.plugin())
        api(libs.plugins.comGithub7hensEzgradle.plugin())
        api(libs.plugins.comGoogleAndroidLibrariesMapsplatformSecretsGradlePlugin.plugin())
        api(libs.plugins.comGoogleDaggerHiltAndroid.plugin())
        api(libs.plugins.comGoogleDevtoolsKsp.plugin())
        api(libs.plugins.orgJetbrainsKotlinAndroid.plugin())
        api(libs.plugins.orgJetbrainsKotlinJvm.plugin())
        api(libs.plugins.orgJetbrainsKotlinPluginPercelize.plugin())
        api(libs.plugins.orgJetbrainsKotlinPluginSerialization.plugin())
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
