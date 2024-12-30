package com.composetest.core.data.di

import com.composetest.core.data.repositories.AnalyticsRepositoryImpl
import com.composetest.core.data.repositories.AuthenticationRepositoryImpl
import com.composetest.core.data.repositories.BiometricRepositoryImpl
import com.composetest.core.data.repositories.SecretKeyRepositoryImpl
import com.composetest.core.data.repositories.UserRepositoryImpl
import com.composetest.core.domain.managers.BiometricRepository
import com.composetest.core.domain.repositories.AnalyticsRepository
import com.composetest.core.domain.repositories.AuthenticationRepository
import com.composetest.core.domain.repositories.SecretKeyRepository
import com.composetest.core.domain.repositories.UserRepository
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
    abstract fun biometricRepository(
        biometricRepositoryImpl: BiometricRepositoryImpl
    ): BiometricRepository


    // Remotes
    @Binds
    abstract fun authenticationRepository(
        authenticationRepositoryImpl: AuthenticationRepositoryImpl
    ): AuthenticationRepository
}