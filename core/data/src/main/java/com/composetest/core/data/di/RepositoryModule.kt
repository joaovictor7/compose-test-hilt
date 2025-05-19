package com.composetest.core.data.di

import com.composetest.core.data.repository.AnalyticsRepositoryImpl
import com.composetest.core.data.repository.DatabaseRepositoryImpl
import com.composetest.core.data.repository.RemoteConfigRepositoryImpl
import com.composetest.core.data.repository.SessionRepositoryImpl
import com.composetest.core.data.repository.SystemBarsThemeRepositoryImpl
import com.composetest.core.domain.repository.AnalyticsRepository
import com.composetest.core.domain.repository.DatabaseRepository
import com.composetest.core.domain.repository.RemoteConfigRepository
import com.composetest.core.domain.repository.SessionRepository
import com.composetest.core.domain.repository.SystemBarsThemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    // Locals
    @Binds
    abstract fun analyticsRepository(
        analyticsRepositoryImpl: AnalyticsRepositoryImpl
    ): AnalyticsRepository

    @Binds
    abstract fun databaseRepository(
        databaseRepositoryImpl: DatabaseRepositoryImpl
    ): DatabaseRepository

    @Binds
    abstract fun systemBarsThemeRepository(
        systemBarsThemeRepositoryImpl: SystemBarsThemeRepositoryImpl
    ): SystemBarsThemeRepository

    @Binds
    abstract fun sessionRepository(
        sessionRepositoryImpl: SessionRepositoryImpl
    ): SessionRepository

    // Remotes
    @Binds
    abstract fun remoteConfigRepository(
        remoteConfigRepositoryImpl: RemoteConfigRepositoryImpl
    ): RemoteConfigRepository
}