package com.shinmiy.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

interface BarcodeRepository {
    suspend fun getAll(): List<Barcode>
    suspend fun addBarcode(barcode: Barcode)
    suspend fun delete(id: String)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BarcodeRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindBarcodeRepository(barcodeRepositoryImpl: BarcodeRepositoryImpl): BarcodeRepository
}

class BarcodeRepositoryImpl @Inject constructor(private val db: AppDatabase) : BarcodeRepository {
    override
    suspend fun getAll(): List<Barcode> = db.barcodeDao().getAll()

    override
    suspend fun addBarcode(barcode: Barcode) = db.barcodeDao().insert(barcode)

    override
    suspend fun delete(id: String) = db.barcodeDao().delete(id)
}