package com.shinmiy.qrcodereader.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shinmiy.repository.Barcode
import com.shinmiy.repository.BarcodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val repository: BarcodeRepository) : ViewModel() {

    fun saveBarcode(rawBarcodeString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBarcode(Barcode(barcode = rawBarcodeString, createdOn = Date()))
        }
    }
}
