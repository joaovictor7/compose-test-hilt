package com.composetest.core.data.di

import com.composetest.common.providers.DateTimeProvider
import com.composetest.common.providers.DispatcherProvider
import com.composetest.common.providers.LocaleProvider
import com.composetest.common.providers.NetworkProvider
import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.providers.AssetsProviderImpl
import com.composetest.core.data.providers.DateTimeProviderImpl
import com.composetest.core.data.providers.DispatcherProviderImpl
import com.composetest.core.data.providers.EnvironmentInstanceProviderImpl
import com.composetest.core.data.providers.EnvironmentInstanceProvider
import com.composetest.core.data.providers.LocaleProviderImpl
import com.composetest.core.data.providers.NetworkProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {

    @Binds
    abstract fun dispatcherProvider(
        dispatcherProviderImpl: DispatcherProviderImpl
    ): DispatcherProvider

    @Binds
    abstract fun networkProvider(
        networkProviderImpl: NetworkProviderImpl
    ): NetworkProvider

    @Binds
    abstract fun localeProvider(
        localeProviderImpl: LocaleProviderImpl
    ): LocaleProvider

    @Binds
    abstract fun dateTimeProvider(
        dateTimeProviderImpl: DateTimeProviderImpl
    ): DateTimeProvider

    @Binds
    abstract fun assetsProvider(
        assetsProviderImpl: AssetsProviderImpl
    ): AssetsProvider

    @Binds
    abstract fun environmentInstanceProvider(
        environmentInstanceProviderImpl: EnvironmentInstanceProviderImpl
    ): EnvironmentInstanceProvider
}