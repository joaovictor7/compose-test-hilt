package com.composetest.core.data.di

import com.composetest.common.provider.DateTimeProvider
import com.composetest.common.provider.DispatcherProvider
import com.composetest.common.provider.LocaleProvider
import com.composetest.common.provider.NetworkProvider
import com.composetest.core.data.provider.AssetsProvider
import com.composetest.core.data.provider.AssetsProviderImpl
import com.composetest.core.data.provider.DateTimeProviderImpl
import com.composetest.core.data.provider.DispatcherProviderImpl
import com.composetest.core.data.provider.EnvironmentInstanceProvider
import com.composetest.core.data.provider.EnvironmentInstanceProviderImpl
import com.composetest.core.data.provider.LocaleProviderImpl
import com.composetest.core.data.provider.NetworkProviderImpl
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