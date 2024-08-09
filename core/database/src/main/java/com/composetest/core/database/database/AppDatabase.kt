package com.composetest.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.composetest.core.database.constants.DatabaseConfig
import com.composetest.core.database.converters.LocalDateTimeConverter
import com.composetest.core.database.daos.SessionEntityDao
import com.composetest.core.database.daos.UserEntityDao
import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.database.entities.UserEntity

@Database(
    version = DatabaseConfig.DATABASE_VERSION,
    exportSchema = false,
    entities = [
        SessionEntity::class,
        UserEntity::class
    ]
)
@TypeConverters(LocalDateTimeConverter::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun userEntityDao(): UserEntityDao
    abstract fun sessionEntityDao(): SessionEntityDao
}