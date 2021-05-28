package com.shinmiy.qrcodereader

import android.Manifest
import android.os.Bundle
import android.util.Size
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import com.shinmiy.qrcodereader.databinding.FragmentCameraBinding

@androidx.camera.core.ExperimentalGetImage
class CameraFragment : Fragment(R.layout.fragment_camera) {

    private val binding: FragmentCameraBinding by lazy { FragmentCameraBinding.bind(requireView()) }
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when {
            // Check if app has permission to use camera
            requireContext().hasPermission(Manifest.permission.CAMERA) -> launchCamera()
            // Check if app should show request dialog
            // "This method returns true if the app has requested this permission previously and the user denied the request."
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> Unit // TODO: show dialog explaining need to use camera
            // App should ask for permission to use camera
            else -> requestPermission(Manifest.permission.CAMERA, this::launchCamera, requireActivity()::finish)
        }
    }

    private fun launchCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(
            {
                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()

                val imageAnalysis = ImageAnalysis.Builder()
                    .setTargetResolution(Size(1280, 720))
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .apply {
                        setAnalyzer(Runnable::run, BarcodeAnalyzer { barcodes ->
                            barcodes.forEach {
                                // TODO: do something with barcodes
                                println("${it.url?.url}")
                            }
                            clearAnalyzer()
                            cameraProviderFuture.get().unbindAll()
                        })
                    }

                val preview = Preview.Builder().build().apply {
                    setSurfaceProvider(binding.previewView.surfaceProvider)
                }

                cameraProviderFuture
                    .get()
                    .bindToLifecycle(this, cameraSelector, imageAnalysis, preview)
            },
            ContextCompat.getMainExecutor(requireContext()),
        )
    }
}

@androidx.camera.core.ExperimentalGetImage
private class BarcodeAnalyzer(private val onScan: (List<Barcode>) -> Unit) : ImageAnalysis.Analyzer {

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