package com.example.vladan.firstkotlinapp.data.db

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by Vladan on 13.12.2017..
 */

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}