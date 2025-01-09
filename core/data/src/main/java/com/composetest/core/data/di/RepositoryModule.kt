package com.composetest.core.data.di

import com.composetest.core.data.repositories.AnalyticsRepositoryImpl
import com.composetest.core.data.repositories.AuthenticationRepositoryImpl
import com.composetest.core.data.repositories.ConfigurationRepositoryImpl
import com.composetest.core.data.repositories.SecretKeyRepositoryImpl
import com.composetest.core.data.repositories.UserRepositoryImpl
import com.composetest.core.data.repositories.WeatherForecastRepositoryImpl
import com.composetest.core.domain.repositories.AnalyticsRepository
import com.composetest.core.domain.repositories.AuthenticationRepository
import com.composetest.core.domain.repositories.ConfigurationRepository
import com.composetest.core.domain.repositories.SecretKeyRepository
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
    abstract fun secretKeyRepository(
        secretKeyRepositoryImpl: SecretKeyRepositoryImpl
    ): SecretKeyRepository

    @Binds
    abstract fun configurationRepository(
        configurationRepositoryImpl: ConfigurationRepositoryImpl
    ): ConfigurationRepository

    // Remotes
    @Binds
    abstract fun authenticationRepository(
        authenticationRepositoryImpl: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    @Binds
    abstract fun weatherForecastRepository(
        weatherForecastRepositoryImpl: WeatherForecastRepositoryImpl
    ): WeatherForecastRepository
}