package com.composetest.core.database.impl.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalDateTime
import javax.inject.Inject

@ProvidedTypeConverter
internal class LocalDateTimeConverter @Inject constructor() {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? =
        value?.let { LocalDateTime.parse(it) }

    @TypeConverter
    fun toTimestamp(date: LocalDateTime?) = date?.toString()
}