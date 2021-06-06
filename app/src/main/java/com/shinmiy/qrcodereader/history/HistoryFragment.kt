package com.shinmiy.qrcodereader.history

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shinmiy.qrcodereader.R
import com.shinmiy.qrcodereader.databinding.FragmentHistoryBinding
import com.shinmiy.repository.Barcode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {
    private val viewModel: HistoryViewModel by viewModels()

    @ExperimentalMaterialApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentHistoryBinding.bind(view)
        binding.composeView.setContent {
            MaterialTheme {
                HistoryScreen(viewModel, onClick = {
                    showBottomSheet(barcode = it.barcode, onDismiss = {})
                })
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun HistoryScreen(viewModel: HistoryViewModel, onClick: (barcode: Barcode) -> Unit) {
    val barcodes = viewModel.barcodeFlow.collectAsState(listOf())
    HistoryList(barcodes = barcodes.value, onClick = onClick)
}

@ExperimentalMaterialApi
@Composable
fun HistoryList(barcodes: List<Barcode>, onClick: (barcode: Barcode) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(barcodes) { barcode: Barcode ->
            HistoryListItem(barcode = barcode, onClick = onClick)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun HistoryListItem(barcode: Barcode, onClick: (barcode: Barcode) -> Unit) {
    ListItem(
        // ExperimentalMaterialApi
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(barcode) },
        text = { Text(barcode.barcode) },
        secondaryText = { Text(barcode.createdOn.toString()) },
    )
}
