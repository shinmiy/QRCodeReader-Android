package com.shinmiy.qrcodereader

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun Context.hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Fragment.requestPermission(permission: String, granted: () -> Unit, dismissed: () -> Unit) {
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { if (it) granted() else dismissed() }.launch(permission)
}

suspend fun Context.retrieveCamera() = suspendCoroutine<ProcessCameraProvider> { continuation ->
    val provider = ProcessCameraProvider.getInstance(this)
    provider.addListener(
        { continuation.resume(provider.get()) },
        ContextCompat.getMainExecutor(this),
    )
}
