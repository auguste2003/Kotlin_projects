package com.example.todo_app_room.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

class Converters {
    @TypeConverter
    fun fromData(date : Date):Long{
        return date.time
    }
    @TypeConverter
    fun toDate(time : Long):Date{
        return Date(time)
    }
}