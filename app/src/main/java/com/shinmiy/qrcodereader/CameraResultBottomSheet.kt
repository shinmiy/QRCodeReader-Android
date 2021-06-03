package com.shinmiy.qrcodereader

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shinmiy.qrcodereader.databinding.FragmentCameraResultBinding

class CameraResultBottomSheet : BottomSheetDialogFragment() {
    private var onDismiss: (() -> Unit)? = null

    private lateinit var binding: FragmentCameraResultBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCameraResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    @ExperimentalMaterialApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val barcode = CameraResultBottomSheetArgs.fromBundle(requireArguments()).barcode
        binding.composeView.setContent {
            MaterialTheme {
                BottomSheet(barcode, {
                    // Copy to clipboard
                    val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("simple text", "Hello, World!")
                    clipboard.setPrimaryClip(clip)
                    Toast.makeText(requireContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show()
                }, {
                    // Open URL
                    val i = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(barcode)
                    }
                    startActivity(i)
                    dismiss()
                })
            }
        }
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismiss?.invoke()
    }

    fun setOnBottomSheetDismiss(onDismiss: () -> Unit) {
        this.onDismiss = onDismiss
    }
}

@ExperimentalMaterialApi
@Composable
fun BottomSheet(barcode: String, onCopy: () -> Unit, onOpen: () -> Unit) {
    Column {
        ListItem(text = { Text(barcode) })
        Divider()
        ListItem(
            text = { Text("Copy") },
            modifier = Modifier.clickable { onCopy() }
        )
        ListItem(
            text = { Text("Open") },
            modifier = Modifier.clickable { onOpen() }
        )
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun ButtomSheetPreview() {
    BottomSheet(barcode = "hogemoge", {}, {})
}