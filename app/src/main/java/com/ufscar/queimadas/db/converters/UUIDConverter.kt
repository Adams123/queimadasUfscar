package com.ufscar.queimadas.db.converters

import androidx.room.TypeConverter
import java.util.*

class UUIDConverter {

    @TypeConverter
    fun uuidToString(value: UUID?): String?{
        return value?.toString()
    }

    @TypeConverter
    fun stringToUUID(value: String?): UUID?{
        return UUID.fromString(value)
    }
}