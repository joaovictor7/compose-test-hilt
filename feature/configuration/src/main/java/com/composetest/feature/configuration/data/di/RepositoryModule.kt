package com.composetest.feature.configuration.data.di

import com.composetest.core.domain.repositories.ConfigurationRepository
import com.composetest.feature.configuration.data.repositories.ConfigurationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun configurationRepository(
        configurationRepositoryImpl: ConfigurationRepositoryImpl
    ): ConfigurationRepository
}