package com.shinmiy.qrcodereader

object Versions {
    const val compose = "1.0.0-beta08"
    const val coroutines = "1.4.3"
}

object Dep {
    object Compose {
        const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val viewbinding = "androidx.compose.ui:ui-viewbinding:${Versions.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val layout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}1"
        const val livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val themeAdapter = "com.google.android.material:compose-theme-adapter:${Versions.compose}"
    }

    object Kotlin {
//        const val bom = "org.jetbrains.kotlin:kotlin-bom:1.4.32"
//
//        // bom import does not working...
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10"
//        const val serializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:1.4.32"
//        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib"
//        const val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.32"
//        const val reflect = "org.jetbrains.kotlin:kotlin-reflect"
    }

    object Coroutines {
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3"
    }

//    object Serialization {
//        const val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.1"
//    }

//    object KotlinTest {
//        const val common = "org.jetbrains.kotlin:kotlin-test-common"
//        const val annotationCommon = "org.jetbrains.kotlin:kotlin-test-annotations-common"
//        const val junit = "org.jetbrains.kotlin:kotlin-test-junit"
//    }

    object Google {
        const val material = "com.google.android.material:material:1.3.0"
        const val barcode = "com.google.mlkit:barcode-scanning:16.1.2"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.5.0-beta01"
        const val appcompat = "androidx.appcompat:appcompat:1.3.0"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-alpha01"
        const val fragment = "androidx.fragment:fragment-ktx:1.3.4"

        const val activity = "androidx.activity:activity-ktx:1.2.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-livedata-ktx:2.3.0"
        const val hiltCommon = "androidx.hilt:hilt-common:1.0.0-alpha03"
        const val hiltLifeCycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"

        object Navigation {
            const val fragment = "androidx.navigation:navigation-fragment-ktx:2.3.5"
            const val ui = "androidx.navigation:navigation-ui-ktx:2.3.5"
            const val testing = "androidx.navigation:navigation-testing:2.3.5"
        }

        object Camera {
            const val camera = "androidx.camera:camera-camera2:1.0.0"
            const val lifecycle = "androidx.camera:camera-lifecycle:1.0.0"
            const val cameraView = "androidx.camera:camera-view:1.0.0-alpha24"
        }

        object Test {
            const val ext = "androidx.test.ext:junit:1.1.2"
            const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
        }

        object Room {
            const val ktx = "androidx.room:room-ktx:2.3.0"
            const val runtime = "androidx.room:room-runtime:2.3.0"
            const val compiler = "androidx.room:room-compiler:2.3.0"
            const val testing = "androidx.room:room-testing:2.3.0"
        }
    }

//    const val playServicesOssLicenses = "com.google.android.gms:play-services-oss-licenses:17.0.0"
//
//    object SQLDelight {
//        const val plugin = "com.squareup.sqldelight:gradle-plugin:1.4.4"
//        const val androidDriver = "com.squareup.sqldelight:android-driver:1.4.4"
//        const val coroutinesExtensions = "com.squareup.sqldelight:coroutines-extensions:1.4.4"
//    }
//
//    object Flipper {
//        const val flipper = "com.facebook.flipper:flipper:0.82.1"
//        const val networkPlugin = "com.facebook.flipper:flipper-network-plugin:0.76.0"
//        const val soLoader = "com.facebook.soloader:soloader:0.10.1"
//    }
//
//    const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:1.1.5"
}