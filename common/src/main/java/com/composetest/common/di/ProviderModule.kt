package com.composetest.common.di

import com.composetest.common.providers.DateTimeProvider
import com.composetest.common.providers.DateTimeProviderImpl
import com.composetest.common.providers.NetworkProvider
import com.composetest.common.providers.NetworkProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderBindsModule {
    @Binds
    abstract fun dateTimeProvider(dateTimeProviderImpl: DateTimeProviderImpl): DateTimeProvider

    @Binds
    abstract fun networkProvider(networkProviderImpl: NetworkProviderImpl): NetworkProvider
}