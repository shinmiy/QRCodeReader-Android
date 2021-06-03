package com.shinmiy.qrcodereader

import android.os.Bundle
import android.util.Size
import android.view.View
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.shinmiy.qrcodereader.databinding.FragmentCameraBinding
import kotlinx.coroutines.launch

@androidx.camera.core.ExperimentalGetImage
class CameraFragment : Fragment(R.layout.fragment_camera) {

    private val binding: FragmentCameraBinding by lazy { FragmentCameraBinding.bind(requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launchCamera()
            }
        }
    }

    private suspend fun launchCamera() {
        val cameraProvider = requireContext().retrieveCamera()
        cameraProvider.unbindAll()

        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        val imageAnalysis = ImageAnalysis.Builder()
            .setTargetResolution(Size(1280, 720))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .apply {
                setAnalyzer(Runnable::run, BarcodeAnalyzer { barcodes ->
                    val rawBarcodeString = barcodes.first().rawValue ?: return@BarcodeAnalyzer
                    showBottomSheet(rawBarcodeString) {
                        viewLifecycleOwner.lifecycleScope.launchWhenResumed { launchCamera() }
                    }
                    clearAnalyzer()
                    cameraProvider.unbindAll()
                })
            }

        val preview = Preview.Builder().build().apply {
            setSurfaceProvider(binding.previewView.surfaceProvider)
        }

        cameraProvider.bindToLifecycle(viewLifecycleOwner, cameraSelector, imageAnalysis, preview)
    }
}

private fun CameraFragment.showBottomSheet(barcode: String, onDismiss: () -> Unit) {
    CameraResultBottomSheet().apply {
        arguments = CameraResultBottomSheetArgs(barcode).toBundle()
        setOnBottomSheetDismiss(onDismiss)
    }.show(childFragmentManager, "")
}