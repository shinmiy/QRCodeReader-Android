package com.shinmiy.qrcodereader.launch

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.shinmiy.qrcodereader.R
import com.shinmiy.qrcodereader.hasPermission
import com.shinmiy.qrcodereader.requestPermission
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchFragment : Fragment(R.layout.fragment_launch) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when {
            // Check if app has permission to use camera
            requireContext().hasPermission(Manifest.permission.CAMERA) -> navigateToCameraFragment()
            // Check if app should show request dialog
            // "This method returns true if the app has requested this permission previously and the user denied the request."
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> Unit // TODO: show dialog explaining need to use camera
            // App should ask for permission to use camera
            else -> requestPermission(Manifest.permission.CAMERA, this::navigateToCameraFragment, requireActivity()::finish)
        }
    }
}

