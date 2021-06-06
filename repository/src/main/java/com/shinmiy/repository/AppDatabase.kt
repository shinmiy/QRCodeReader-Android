package com.shinmiy.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [Barcode::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun barcodeDao(): BarcodeDao
}

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModule {
    @Provides
    fun getDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "barcodes").build()

    @Provides
    fun getDao(db: AppDatabase) = db.barcodeDao()
}