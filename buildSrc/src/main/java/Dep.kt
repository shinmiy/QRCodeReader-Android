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
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10"
    }

    object Coroutines {
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3"
    }

    object Google {
        const val material = "com.google.android.material:material:1.3.0"
        const val barcode = "com.google.mlkit:barcode-scanning:16.1.2"
    }

    object Hilt {
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:2.36"
        const val android = "com.google.dagger:hilt-android:2.36"
        const val compiler = "com.google.dagger:hilt-compiler:2.36"

        const val hiltCommon = "androidx.hilt:hilt-common:1.0.0"
        const val hiltLifeCycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.5.0-beta01"
        const val appcompat = "androidx.appcompat:appcompat:1.3.0"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-alpha01"
        const val fragment = "androidx.fragment:fragment-ktx:1.3.4"

        const val activity = "androidx.activity:activity-ktx:1.2.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-livedata-ktx:2.3.0"

        object Navigation {
            const val fragment = "androidx.navigation:navigation-fragment-ktx:2.3.5"
            const val ui = "androidx.navigation:navigation-ui-ktx:2.3.5"
            const val testing = "androidx.navigation:navigation-testing:2.3.5"
        }

        object Camera {
            const val camera = "androidx.camera:camera-camera2:1.0.0"
            const val lifecycle = "androidx.camera:camera-lifecycle:1.0.0"
            const val cameraView = "androidx.camera:camera-view:1.0.0-alpha25"
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
}
