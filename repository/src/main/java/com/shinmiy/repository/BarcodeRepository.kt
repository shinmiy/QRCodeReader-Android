package com.shinmiy.repository

import android.content.Context
import androidx.room.Room

interface BarcodeRepository {
    suspend fun getAll(): List<Barcode>
    suspend fun addBarcode(barcode: Barcode)
    suspend fun delete(id: String)
}

class BarcodeRepositoryImpl(context: Context) : BarcodeRepository {
    private var db: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "barcodes").build()

    override
    suspend fun getAll(): List<Barcode> {
        db.barcodeDao().getAll()
        return listOf()
    }

    override
    suspend fun addBarcode(barcode: Barcode) {
        db.barcodeDao().insert(barcode)
    }

    override
    suspend fun delete(id: String) {
        db.barcodeDao().delete(id)
    }

}