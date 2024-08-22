package com.composetest.core.router.di

import com.composetest.core.router.providers.NavControllerProvider
import com.composetest.core.router.providers.NavControllerProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {

    @Binds
    @Singleton
    abstract fun navControllerProvider(
        navControllerProviderImpl: NavControllerProviderImpl
    ): NavControllerProvider
}