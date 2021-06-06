package com.shinmiy.qrcodereader.camera

import android.os.Bundle
import android.util.Size
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.shinmiy.qrcodereader.R
import com.shinmiy.qrcodereader.databinding.FragmentCameraBinding
import com.shinmiy.qrcodereader.retrieveCamera
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
@androidx.camera.core.ExperimentalGetImage
class CameraFragment : Fragment(R.layout.fragment_camera) {
    private val viewModel: CameraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launchCamera()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.history -> true.also {
                viewLifecycleOwner.lifecycleScope.launch {
                    val cameraProvider = requireContext().retrieveCamera()
                    cameraProvider.unbindAll()
                    navigateToHistoryFragment()
                }
            }
            else -> false
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
                        viewModel.saveBarcode(rawBarcodeString)
                        viewLifecycleOwner.lifecycleScope.launchWhenResumed { launchCamera() }
                    }
                    clearAnalyzer()
                    cameraProvider.unbindAll()
                })
            }

        val preview = Preview.Builder().build().apply {
            val binding = FragmentCameraBinding.bind(requireView())
            setSurfaceProvider(binding.previewView.surfaceProvider)
        }

        cameraProvider.bindToLifecycle(viewLifecycleOwner, cameraSelector, imageAnalysis, preview)
    }
}