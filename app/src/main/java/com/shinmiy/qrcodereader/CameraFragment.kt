package com.shinmiy.qrcodereader

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.common.util.concurrent.ListenableFuture
import com.shinmiy.qrcodereader.databinding.FragmentCameraBinding

class CameraFragment : Fragment(R.layout.fragment_camera) {

    private val binding: FragmentCameraBinding by lazy { FragmentCameraBinding.bind(requireView()) }
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when {
            // Check if app has permission to use camera
            requireContext().hasPermission(Manifest.permission.CAMERA) -> {
                launchCamera()
            }
            // Check if app should show request dialog
            // "This method returns true if the app has requested this permission previously and the user denied the request."
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                // TODO: show dialog explaining need to use camera
            }
            // App should ask for permission to use camera
            else -> {
                val requestPermissionLauncher = registerForActivityResult(RequestPermission()) { isGranted ->
                    if (isGranted.not()) requireActivity().finish() // can't do anything without camera
                    launchCamera()
                }
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun launchCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(
            {
                val preview: Preview = Preview.Builder().build()
                val cameraSelector: CameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()
                preview.setSurfaceProvider(binding.previewView.surfaceProvider)

                val camera = cameraProviderFuture.get().bindToLifecycle(this, cameraSelector, preview)
            },
            ContextCompat.getMainExecutor(requireContext()),
        )
    }
}