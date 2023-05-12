object Versions {
    /**
     * Core dependencies
     * */
    const val composeVersion = "1.4.3"
    const val coreKtx = "1.8.0"
    const val lifecycleRuntimeKtx = "2.3.1"
    const val activityCompose = "1.5.1"
    const val composeBom = "2022.10.00"
    const val composeMaterial3 = "1.0.1"

    /**
     * Core Test dependencies
     * */
    const val testImplJunit = "4.13.2"
    const val androidTestImplJunit = "1.1.5"
    const val androidTestEspresso = "3.5.1"

    /**
     * Unit Test dependencies
     * */
    const val mockk = "1.13.4"
    const val mockitoCore = "5.2.0"
    const val robolectric = "4.9.2"
    const val coroutinesTest = "1.6.4"
}

object CoreDependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.composeVersion}"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics:${Versions.composeVersion}"
    const val composeUiToolingPreview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeMaterial3WindowSizeClass =
        "androidx.compose.material3:material3-window-size-class:${Versions.composeMaterial3}"
}

object TestImplementation {
    const val junit = "junit:junit:${Versions.testImplJunit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
}

object AndroidTestImplementation {
    const val junit = "androidx.test.ext:junit:${Versions.androidTestImplJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.androidTestEspresso}"
    const val uiTestJunit = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
}

object DebugImplementation {
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.composeVersion}"
}

object DaggerHilt {
    private const val hilt_dagger_version = "2.44"
    private const val hilt_compiler_version = "1.0.0"
    private const val hilt_navigation_compose = "1.0.0"
    const val hilt = "com.google.dagger:hilt-android:$hilt_dagger_version"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$hilt_dagger_version"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:$hilt_compiler_version"
    const val hiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:$hilt_navigation_compose"
}

object Exoplayer {
    private const val exoplayer_version = "1.0.1"
    const val exoplayer = "androidx.media3:media3-exoplayer:$exoplayer_version"
    const val exoplayerUi = "androidx.media3:media3-ui:$exoplayer_version"
    const val exoplayerDash = "androidx.media3:media3-exoplayer-dash:$exoplayer_version"
}

object Retrofit {
    private const val retrofit_version = "2.9.0"
    private const val logging_interceptor_version = "5.0.0-alpha.3"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:$retrofit_version"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:$logging_interceptor_version"
}

object Paging {
    private const val paging_version = "1.0.0-alpha19"
    const val paging = "androidx.paging:paging-compose:$paging_version"
}

object Glide {
    private const val glide_version = "0.10.0"
    const val glide = "com.google.accompanist:accompanist-glide:$glide_version"
}

object Navigation {
    private const val navigation_version = "2.5.3"
    const val navigation = "androidx.navigation:navigation-compose:$navigation_version"
}

object Pager {
    private const val pager_version = "0.24.12-rc"
    const val pager = "com.google.accompanist:accompanist-pager:$pager_version"
}

object Flowlayout {
    private const val flowlayout_version = "0.24.10-beta"
    const val flowlayout = "com.google.accompanist:accompanist-flowlayout:$flowlayout_version"
}

