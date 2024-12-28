package com.composetest.core.database.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.core.database.converters.LocalDateTimeConverter
import com.composetest.core.database.database.AppDatabase
import com.composetest.core.database.managers.DatabaseSecurityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    private const val DATABASE_NAME = "composetest_database"

    @Provides
    @Singleton
    fun appDatabase(
        @ApplicationContext context: Context,
        buildConfigProvider: BuildConfigProvider,
        databaseSecurityManager: DatabaseSecurityManager
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    )
        .openHelperFactory(getHelperFactory(databaseSecurityManager))
        .addTypeConverter(LocalDateTimeConverter())
        .addLogs(buildConfigProvider)
        .build()

    private fun getHelperFactory(databaseSecurityManager: DatabaseSecurityManager) = runBlocking {
        databaseSecurityManager.getDatabaseCipherFactory()
    }

    private fun RoomDatabase.Builder<AppDatabase>.addLogs(
        buildConfigProvider: BuildConfigProvider
    ) = also {
        if (buildConfigProvider.get.isRelease && buildConfigProvider.get.isProduction) return@also
        setQueryCallback({ sqlQuery, bindArgs ->
            Log.i("SQLite", "SQL Query: $sqlQuery")
            if (bindArgs.isNotEmpty()) Log.i("SQLite", "SQL Args: $bindArgs")
        }, Executors.newSingleThreadExecutor())
    }
}