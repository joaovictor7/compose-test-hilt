package com.composetest.core.database.di

import android.content.Context
import androidx.room.Room
import com.composetest.core.database.converters.LocalDateTimeConverter
import com.composetest.core.database.database.AppDatabase
import com.composetest.core.database.domain.constants.DatabaseConfig
import com.composetest.core.database.providers.SqlCipherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    fun appDatabase(
        @ApplicationContext context: Context,
        sqlCipherProvider: SqlCipherProvider
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DatabaseConfig.DATABASE_NAME
    ).openHelperFactory(sqlCipherProvider.getFactory())
        .addTypeConverter(LocalDateTimeConverter())
        .build()
}