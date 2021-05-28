package com.shinmiy.qrcodereader

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Context.hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Fragment.requestPermission(permission: String, granted: () -> Unit, dismissed: () -> Unit) {
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { if (it) granted() else dismissed() }.launch(permission)
}