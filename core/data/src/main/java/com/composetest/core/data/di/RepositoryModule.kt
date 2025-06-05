package com.composetest.core.data.di

import com.composetest.core.data.repository.RemoteConfigRepositoryImpl
import com.composetest.core.data.repository.SystemBarsThemeRepositoryImpl
import com.composetest.core.domain.repository.RemoteConfigRepository
import com.composetest.core.domain.repository.SystemBarsThemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun systemBarsThemeRepository(
        systemBarsThemeRepositoryImpl: SystemBarsThemeRepositoryImpl
    ): SystemBarsThemeRepository

    @Binds
    abstract fun remoteConfigRepository(
        remoteConfigRepositoryImpl: RemoteConfigRepositoryImpl
    ): RemoteConfigRepository
}