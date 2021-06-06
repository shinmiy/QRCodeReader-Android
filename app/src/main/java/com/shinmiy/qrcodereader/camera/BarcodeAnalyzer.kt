package com.shinmiy.qrcodereader.camera

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

@androidx.camera.core.ExperimentalGetImage
class BarcodeAnalyzer(private val onScan: (List<Barcode>) -> Unit) : ImageAnalysis.Analyzer {

    private val scannerOptions = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_EAN_13,
            Barcode.FORMAT_EAN_8,
        ).build()

    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: return
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        BarcodeScanning.getClient(scannerOptions).process(image)
            .addOnSuccessListener { barcodes ->
                if (barcodes.isEmpty()) return@addOnSuccessListener
                onScan(barcodes)
            }
            .addOnFailureListener { println(it) }
            .addOnCompleteListener { imageProxy.close() }
    }

}