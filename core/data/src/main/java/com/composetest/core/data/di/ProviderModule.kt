package com.composetest.core.data.di

import com.composetest.core.data.providers.FakeInstanceProvider
import com.composetest.core.data.providers.FakeInstanceProviderImpl
import com.composetest.core.data.providers.NetworkProvider
import com.composetest.core.data.providers.NetworkProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {

    @Binds
    abstract fun fakeInstanceProvider(
        fakeInstanceProviderImpl: FakeInstanceProviderImpl
    ): FakeInstanceProvider

    @Binds
    abstract fun networkProvider(networkProviderImpl: NetworkProviderImpl): NetworkProvider
}