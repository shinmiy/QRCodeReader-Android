package com.shinmiy.qrcodereader.launch

import androidx.navigation.fragment.findNavController

fun LaunchFragment.navigateToCameraFragment() {
    LaunchFragmentDirections.actionLaunchFragmentToCameraFragment()
        .let(findNavController()::navigate)
}