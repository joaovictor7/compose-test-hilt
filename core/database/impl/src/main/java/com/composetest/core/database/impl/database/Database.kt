package com.composetest.core.database.impl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.composetest.core.database.androidapi.data.entity.SessionEntity
import com.composetest.core.database.androidapi.data.entity.UserEntity
import com.composetest.core.database.androidapi.data.entity.configuration.ConfigurationEntity
import com.composetest.core.database.impl.data.converter.LocalDateTimeConverter
import com.composetest.core.database.androidapi.dao.ConfigurationEntityDao
import com.composetest.core.database.androidapi.dao.SessionEntityDao
import com.composetest.core.database.androidapi.dao.UserEntityDao

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