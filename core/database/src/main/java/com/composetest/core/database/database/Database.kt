package com.composetest.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.composetest.core.database.data.converter.LocalDateTimeConverter
import com.composetest.core.database.data.dao.ConfigurationEntityDao
import com.composetest.core.database.data.dao.SessionEntityDao
import com.composetest.core.database.data.dao.UserEntityDao
import com.composetest.core.database.data.entity.SessionEntity
import com.composetest.core.database.data.entity.UserEntity
import com.composetest.core.database.data.entity.configuration.ConfigurationEntity

private const val DATABASE_VERSION = 1

@Database(
    version = DATABASE_VERSION,
    exportSchema = false,
    entities = [
        SessionEntity::class,
        UserEntity::class,
        ConfigurationEntity::class
    ]
)
@TypeConverters(LocalDateTimeConverter::class)
internal abstract class Database : RoomDatabase() {
    abstract fun userEntityDao(): UserEntityDao
    abstract fun sessionEntityDao(): SessionEntityDao
    abstract fun configurationEntityDao(): ConfigurationEntityDao
}