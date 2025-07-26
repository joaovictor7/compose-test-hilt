package com.composetest.core.database.impl.di

import com.composetest.core.database.androidapi.dao.ConfigurationEntityDao
import com.composetest.core.database.androidapi.dao.SessionEntityDao
import com.composetest.core.database.androidapi.dao.UserEntityDao
import com.composetest.core.database.impl.database.Database
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