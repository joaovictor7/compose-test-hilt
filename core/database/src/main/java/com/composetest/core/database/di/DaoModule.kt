package com.composetest.core.database.di

import com.composetest.core.database.dao.ConfigurationEntityDao
import com.composetest.core.database.dao.SessionEntityDao
import com.composetest.core.database.dao.UserEntityDao
import com.composetest.core.database.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    fun sessionEntityDao(database: Database): SessionEntityDao =
        database.sessionEntityDao()

    @Provides
    fun userEntityDao(database: Database): UserEntityDao = database.userEntityDao()

    @Provides
    fun configurationEntityDao(
        database: Database
    ): ConfigurationEntityDao = database.configurationEntityDao()
}