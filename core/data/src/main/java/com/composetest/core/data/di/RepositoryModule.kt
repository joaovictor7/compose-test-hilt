package com.composetest.core.data.di

import com.composetest.core.data.repositories.AnalyticsRepositoryImpl
import com.composetest.core.data.repositories.AuthenticationRepositoryImpl
import com.composetest.core.data.repositories.ConfigurationRepositoryImpl
import com.composetest.core.data.repositories.DatabaseRepositoryImpl
import com.composetest.core.data.repositories.ExchangeRepositoryImpl
import com.composetest.core.data.repositories.NewsApiRepositoryImpl
import com.composetest.core.data.repositories.RemoteConfigRepositoryImpl
import com.composetest.core.data.repositories.SessionRepositoryImpl
import com.composetest.core.data.repositories.SystemBarsThemeRepositoryImpl
import com.composetest.core.data.repositories.UserRepositoryImpl
import com.composetest.core.data.repositories.WeatherForecastRepositoryImpl
import com.composetest.core.domain.repositories.AnalyticsRepository
import com.composetest.core.domain.repositories.AuthenticationRepository
import com.composetest.core.domain.repositories.ConfigurationRepository
import com.composetest.core.domain.repositories.DatabaseRepository
import com.composetest.core.domain.repositories.ExchangeRepository
import com.composetest.core.domain.repositories.NewsApiRepository
import com.composetest.core.domain.repositories.RemoteConfigRepository
import com.composetest.core.domain.repositories.SessionRepository
import com.composetest.core.domain.repositories.SystemBarsThemeRepository
import com.composetest.core.domain.repositories.UserRepository
import com.composetest.core.domain.repositories.WeatherForecastRepository
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
    abstract fun userRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun databaseRepository(
        databaseRepositoryImpl: DatabaseRepositoryImpl
    ): DatabaseRepository

    @Binds
    abstract fun configurationRepository(
        configurationRepositoryImpl: ConfigurationRepositoryImpl
    ): ConfigurationRepository

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
    abstract fun authenticationRepository(
        authenticationRepositoryImpl: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    @Binds
    abstract fun weatherForecastRepository(
        weatherForecastRepositoryImpl: WeatherForecastRepositoryImpl
    ): WeatherForecastRepository

    @Binds
    abstract fun newsApiRepository(
        newsRepositoryImpl: NewsApiRepositoryImpl
    ): NewsApiRepository

    @Binds
    abstract fun exchangeRepository(
        exchangeRepositoryImpl: ExchangeRepositoryImpl
    ): ExchangeRepository

    @Binds
    abstract fun remoteConfigRepository(
        remoteConfigRepositoryImpl: RemoteConfigRepositoryImpl
    ): RemoteConfigRepository
}