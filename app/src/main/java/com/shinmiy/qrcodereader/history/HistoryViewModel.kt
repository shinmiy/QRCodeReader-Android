package com.shinmiy.qrcodereader.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shinmiy.repository.Barcode
import com.shinmiy.repository.BarcodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: BarcodeRepository) : ViewModel() {

    private val _barcodeFlow = MutableStateFlow<List<Barcode>>(listOf())
    val barcodeFlow: StateFlow<List<Barcode>> = _barcodeFlow

    init {
        viewModelScope.launch {
            _barcodeFlow.value = repository.getAll()
        }
    }
}