package com.shinmiy.qrcodereader

object Versions {
    const val compose = "1.2.1"
    const val coroutines = "1.4.3"
}

object Dep {
    object Compose {
        const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val viewbinding = "androidx.compose.ui:ui-viewbinding:${Versions.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val layout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val themeAdapter = "com.google.android.material:compose-theme-adapter:1.1.20"
    }

    object Kotlin {
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20"
    }

    object Coroutines {
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3"
    }

    object Google {
        const val material = "com.google.android.material:material:1.3.0"
        const val barcode = "com.google.mlkit:barcode-scanning:16.1.2"
    }

    object Hilt {
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:2.44"
        const val android = "com.google.dagger:hilt-android:2.44"
        const val compiler = "com.google.dagger:hilt-compiler:2.44"

        const val hiltCommon = "androidx.hilt:hilt-common:1.0.0"
        const val hiltLifeCycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.9.0"
        const val appcompat = "androidx.appcompat:appcompat:1.5.1"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
        const val fragment = "androidx.fragment:fragment-ktx:1.5.3"

        const val activity = "androidx.activity:activity-ktx:1.6.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-livedata-ktx:2.5.1"

        object Navigation {
            const val fragment = "androidx.navigation:navigation-fragment-ktx:2.5.2"
            const val ui = "androidx.navigation:navigation-ui-ktx:2.5.2"
            const val testing = "androidx.navigation:navigation-testing:2.5.2"
        }

        object Camera {
            const val camera = "androidx.camera:camera-camera2:1.1.0"
            const val lifecycle = "androidx.camera:camera-lifecycle:1.1.0"
            const val cameraView = "androidx.camera:camera-view:1.1.0"
        }

        object Test {
            const val ext = "androidx.test.ext:junit:1.1.2"
            const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
        }

        object Room {
            const val ktx = "androidx.room:room-ktx:2.4.3"
            const val runtime = "androidx.room:room-runtime:2.4.3"
            const val compiler = "androidx.room:room-compiler:2.4.3"
            const val testing = "androidx.room:room-testing:2.4.3"
        }
    }
}
