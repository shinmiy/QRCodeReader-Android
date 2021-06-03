package com.shinmiy.qrcodereader

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController

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

private fun LaunchFragment.navigateToCameraFragment() {
    LaunchFragmentDirections.actionLaunchFragmentToCameraFragment()
        .let(findNavController()::navigate)
}
