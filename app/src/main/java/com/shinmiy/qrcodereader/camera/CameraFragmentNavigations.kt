package com.shinmiy.qrcodereader.camera

import androidx.navigation.fragment.findNavController
import com.shinmiy.qrcodereader.result.CameraResultBottomSheet
import com.shinmiy.qrcodereader.result.CameraResultBottomSheetArgs

fun CameraFragment.showBottomSheet(barcode: String, onDismiss: () -> Unit) {
    CameraResultBottomSheet().apply {
        arguments = CameraResultBottomSheetArgs(barcode).toBundle()
        setOnBottomSheetDismiss(onDismiss)
    }.show(childFragmentManager, "")
}

fun CameraFragment.navigateToHistoryFragment() {
    CameraFragmentDirections.actionCameraFragmentToHistoryFragment()
        .let(findNavController()::navigate)
}