package com.shinmiy.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BarcodeDao {
    @Query("SELECT * FROM barcode")
    suspend fun getAll(): List<Barcode>

    @Insert
    suspend fun insert(barcode: Barcode)

    @Query("DELETE FROM barcode WHERE id = :id")
    suspend fun delete(id: String)
}