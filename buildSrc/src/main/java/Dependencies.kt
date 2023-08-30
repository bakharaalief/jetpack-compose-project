object Dependencies {
    //implementation
    private const val coreKtx = "androidx.core:core-ktx:1.9.0"
    private const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
    private const val activityCompose = "androidx.activity:activity-compose:1.7.0"
    private const val composeUi = "androidx.compose.ui:ui"
    private const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    private const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    private const val composeMaterial3 = "androidx.compose.material3:material3"
    private const val glideCompose = "com.github.bumptech.glide:compose:1.0.0-alpha.5"
    private const val coilCompose = "io.coil-kt:coil-compose:2.4.0"

    //implementation && android test implementation (platform)
    private const val composeBom = "androidx.compose:compose-bom:2023.03.00"

    //test implementation
    private const val junit = "junit:junit:4.13.2"

    //android test implementation
    private const val junitExt = "androidx.test.ext:junit:1.1.5"
    private const val espressoCore = "androidx.test.espresso:espresso-core:3.5.1"
    private const val composeUiTestJunit = "androidx.compose.ui:ui-test-junit4"

    //debug implementation
    private const val uiTooling = "androidx.compose.ui:ui-tooling"
    private const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"

    val implementation = arrayListOf<String>().apply {
        add(coreKtx)
        add(lifecycleRuntimeKtx)
        add(activityCompose)
        add(composeUi)
        add(composeUiGraphics)
        add(composeUiToolingPreview)
        add(composeMaterial3)
//        add(glideCompose)
        add(coilCompose)
    }

    val platform = arrayListOf<String>().apply {
        add(composeBom)
    }

    val testImplemantation = arrayListOf<String>().apply {
        add(junit)
    }

    val androidTestImplementation = arrayListOf<String>().apply {
        add(junitExt)
        add(espressoCore)
        add(composeUiTestJunit)
    }

    val debugImplemtation = arrayListOf<String>().apply {
        add(uiTooling)
        add(uiTestManifest)
    }
}