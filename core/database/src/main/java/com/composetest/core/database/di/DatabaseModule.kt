package com.composetest.core.database.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.core.database.converters.LocalDateTimeConverter
import com.composetest.core.database.database.AppDatabase
import com.composetest.core.database.database.DATABASE_NAME
import com.composetest.core.domain.usecases.GetSqliteSecretKeyUseCase
import com.composetest.core.security.providers.SqliteCipherProvider
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

    @Provides
    @Singleton
    fun appDatabase(
        @ApplicationContext context: Context,
        buildConfigProvider: BuildConfigProvider,
        sqliteCipherProvider: SqliteCipherProvider,
        getSqliteSecretKeyUseCase: GetSqliteSecretKeyUseCase
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    )
        .openHelperFactory(getSecretKey(getSqliteSecretKeyUseCase, sqliteCipherProvider))
        .addTypeConverter(LocalDateTimeConverter())
        .apply {
            if (!buildConfigProvider.get.isRelease) {
                setQueryCallback({ sqlQuery, bindArgs ->
                    Log.i("SQLite", "SQL Query: $sqlQuery")
                    if (bindArgs.isNotEmpty()) Log.i("SQLite", "SQL Args: $bindArgs")
                }, Executors.newSingleThreadExecutor())
            }
        }
        .build()

    private fun getSecretKey(
        getSqliteSecretKeyUseCase: GetSqliteSecretKeyUseCase,
        sqliteCipherProvider: SqliteCipherProvider
    ) = runBlocking {
        getSqliteSecretKeyUseCase()?.let { sqliteCipherProvider.getFactory(it) }
    }
}