package com.composetest.core.data.di

import com.composetest.core.data.managers.RemoteConfigManagerImpl
import com.composetest.core.domain.managers.RemoteConfigManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ManagerModule {

    @Binds
    abstract fun remoteConfigManager(
        remoteConfigManagerImpl: RemoteConfigManagerImpl
    ): RemoteConfigManager
}