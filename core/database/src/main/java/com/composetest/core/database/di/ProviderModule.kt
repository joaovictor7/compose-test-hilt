package com.composetest.core.database.di

import com.composetest.core.database.providers.SecretKeyProvider
import com.composetest.core.database.providers.SecretKeyProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {

    @Binds
    abstract fun secretKeyProvider(
        secretKeyProviderImpl: SecretKeyProviderImpl
    ): SecretKeyProvider
}