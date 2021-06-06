package com.shinmiy.qrcodereader

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.shinmiy.repository.Barcode
import com.shinmiy.repository.BarcodeRepository
import com.shinmiy.repository.BarcodeRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class CameraViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BarcodeRepository = BarcodeRepositoryImpl(application)

    fun saveBarcode(rawBarcodeString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBarcode(Barcode(barcode = rawBarcodeString, createdOn = Date()))
        }
    }
}
