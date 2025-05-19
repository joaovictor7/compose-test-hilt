package com.composetest.core.security.di

import com.composetest.core.security.provider.BiometricProvider
import com.composetest.core.security.provider.BiometricProviderImpl
import com.composetest.core.security.provider.CipherProvider
import com.composetest.core.security.provider.CipherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {

    @Binds
    abstract fun cipherProvider(
        cipherProviderImpl: CipherProviderImpl
    ): CipherProvider

    @Binds
    abstract fun biometricsProvider(
        biometricsProviderImpl: BiometricProviderImpl
    ): BiometricProvider
}