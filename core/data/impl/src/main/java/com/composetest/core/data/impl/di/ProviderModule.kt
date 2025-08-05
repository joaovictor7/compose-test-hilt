package com.composetest.core.data.impl.di

import com.composetest.common.api.provider.DateTimeProvider
import com.composetest.common.api.provider.DispatcherProvider
import com.composetest.common.api.provider.LocaleProvider
import com.composetest.common.api.provider.NetworkProvider
import com.composetest.core.data.api.provider.AssetsProvider
import com.composetest.core.data.api.provider.EnvironmentInstanceProvider
import com.composetest.core.data.impl.provider.AssetsProviderImpl
import com.composetest.core.data.impl.provider.DateTimeProviderImpl
import com.composetest.core.data.impl.provider.DispatcherProviderImpl
import com.composetest.core.data.impl.provider.EnvironmentInstanceProviderImpl
import com.composetest.core.data.impl.provider.LocaleProviderImpl
import com.composetest.core.data.impl.provider.NetworkProviderImpl
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