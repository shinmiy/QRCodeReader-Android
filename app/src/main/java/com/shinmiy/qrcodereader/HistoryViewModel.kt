package com.shinmiy.qrcodereader

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.shinmiy.repository.Barcode
import com.shinmiy.repository.BarcodeRepository
import com.shinmiy.repository.BarcodeRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application): AndroidViewModel(application) {
    private val repository: BarcodeRepository = BarcodeRepositoryImpl(application)

    private val _barcodeFlow = MutableStateFlow<List<Barcode>>(listOf())
    val barcodeFlow: StateFlow<List<Barcode>> = _barcodeFlow

    init {
        viewModelScope.launch {
            _barcodeFlow.value = repository.getAll()
        }
    }
}