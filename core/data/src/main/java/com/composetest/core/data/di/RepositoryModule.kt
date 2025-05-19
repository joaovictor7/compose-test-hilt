package com.composetest.core.data.di

import com.composetest.core.data.repositories.AnalyticsRepositoryImpl
import com.composetest.core.data.repositories.DatabaseRepositoryImpl
import com.composetest.core.data.repositories.RemoteConfigRepositoryImpl
import com.composetest.core.data.repositories.SessionRepositoryImpl
import com.composetest.core.data.repositories.SystemBarsThemeRepositoryImpl
import com.composetest.core.domain.repositories.AnalyticsRepository
import com.composetest.core.domain.repositories.DatabaseRepository
import com.composetest.core.domain.repositories.RemoteConfigRepository
import com.composetest.core.domain.repositories.SessionRepository
import com.composetest.core.domain.repositories.SystemBarsThemeRepository
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