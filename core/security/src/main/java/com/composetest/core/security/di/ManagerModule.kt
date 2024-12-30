package com.composetest.core.security.di

import com.composetest.core.security.managers.BiometricManager
import com.composetest.core.security.managers.BiometricManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ManagerModule {
    @Binds
    abstract fun biometricManager(
        biometricManagerImpl: BiometricManagerImpl
    ): BiometricManager
}