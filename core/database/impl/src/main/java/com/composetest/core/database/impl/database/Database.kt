package com.composetest.core.database.impl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.composetest.core.database.androidapi.dao.ConfigurationEntityDao
import com.composetest.core.database.androidapi.dao.ProductEntityDao
import com.composetest.core.database.androidapi.dao.SessionEntityDao
import com.composetest.core.database.androidapi.dao.UserEntityDao
import com.composetest.core.database.androidapi.data.entity.ProductEntity
import com.composetest.core.database.androidapi.data.entity.SessionEntity
import com.composetest.core.database.androidapi.data.entity.UserEntity
import com.composetest.core.database.androidapi.data.entity.configuration.ConfigurationEntity
import com.composetest.core.database.impl.data.converter.LocalDateTimeConverter

private const val DATABASE_VERSION = 1

@Database(
    version = DATABASE_VERSION,
    entities = [
        SessionEntity::class,
        UserEntity::class,
        ConfigurationEntity::class,
        ProductEntity::class
    ]
)
@TypeConverters(LocalDateTimeConverter::class)
internal abstract class Database : RoomDatabase() {
    abstract fun userEntityDao(): UserEntityDao
    abstract fun sessionEntityDao(): SessionEntityDao
    abstract fun configurationEntityDao(): ConfigurationEntityDao
    abstract fun productEntityDao(): ProductEntityDao
}