package com.composetest.core.security.di

import com.composetest.core.security.providers.BiometricsProvider
import com.composetest.core.security.providers.BiometricsProviderImpl
import com.composetest.core.security.providers.CipherProvider
import com.composetest.core.security.providers.CipherProviderImpl
import com.composetest.core.security.providers.SqliteCipherProvider
import com.composetest.core.security.providers.SqliteCipherProviderImpl
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
    abstract fun sqlCipherProvider(
        sqliteCipherProviderImpl: SqliteCipherProviderImpl
    ): SqliteCipherProvider

    @Binds
    abstract fun biometricsProvider(
        biometricsProviderImpl: BiometricsProviderImpl
    ): BiometricsProvider
}