package com.composetest.feature.login.data.di

import com.composetest.core.domain.repository.AuthenticationRepository
import com.composetest.core.domain.repository.SessionRepository
import com.composetest.feature.login.data.repository.AuthenticationRepositoryImpl
import com.composetest.feature.login.data.repository.SessionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun authenticationRepository(
        authenticationRepository: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    @Binds
    abstract fun sessionRepository(
        sessionRepositoryImpl: SessionRepositoryImpl
    ): SessionRepository
}