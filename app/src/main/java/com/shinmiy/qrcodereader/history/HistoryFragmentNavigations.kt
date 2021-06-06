package com.shinmiy.qrcodereader.history

import com.shinmiy.qrcodereader.result.CameraResultBottomSheet
import com.shinmiy.qrcodereader.result.CameraResultBottomSheetArgs

fun HistoryFragment.showBottomSheet(barcode: String, onDismiss: () -> Unit) {
    CameraResultBottomSheet().apply {
        arguments = CameraResultBottomSheetArgs(barcode).toBundle()
        setOnBottomSheetDismiss(onDismiss)
    }.show(childFragmentManager, "")
}
