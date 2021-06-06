package com.shinmiy.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity
data class Barcode(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "barcode") val barcode: String,
    @ColumnInfo(name = "created_on") val createdOn: Date,
)

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}