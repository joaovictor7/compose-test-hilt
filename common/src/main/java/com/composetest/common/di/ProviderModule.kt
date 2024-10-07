package com.composetest.common.di

import com.composetest.common.providers.DispatcherProvider
import com.composetest.common.providers.DispatcherProviderImpl
import com.composetest.common.providers.StringResourceProvider
import com.composetest.common.providers.StringResourceProviderImpl
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
    abstract fun stringResourceProvider(
        stringResourceProviderImpl: StringResourceProviderImpl
    ): StringResourceProvider
}