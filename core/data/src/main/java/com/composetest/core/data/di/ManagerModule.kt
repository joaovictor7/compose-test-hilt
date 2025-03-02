package com.composetest.core.data.di

import com.composetest.core.data.managers.RemoteConfigManagerImpl
import com.composetest.core.data.managers.SessionManagerImpl
import com.composetest.core.data.managers.WorkManager
import com.composetest.core.data.managers.WorkManagerImpl
import com.composetest.core.domain.managers.RemoteConfigManager
import com.composetest.core.domain.managers.SessionManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ManagerModule {

    @Binds
    abstract fun sessionManager(
        sessionManagerImpl: SessionManagerImpl
    ): SessionManager

    @Binds
    abstract fun remoteConfigManager(
        remoteConfigManagerImpl: RemoteConfigManagerImpl
    ): RemoteConfigManager

    @Binds
    abstract fun workManager(
        workManagerImpl: WorkManagerImpl
    ): WorkManager
}