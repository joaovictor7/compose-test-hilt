package com.composetest.core.data.di

import com.composetest.core.domain.managers.AppThemeManager
import com.composetest.core.data.managers.AppThemeManagerImpl
import com.composetest.core.data.managers.RemoteCallManager
import com.composetest.core.data.managers.RemoteCallManagerImpl
import com.composetest.core.data.managers.SessionManagerImpl
import com.composetest.core.data.providers.WorkManagerProvider
import com.composetest.core.data.providers.WorkManagerProviderImpl
import com.composetest.core.domain.managers.SessionManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ManagerModule {

    @Binds
    abstract fun remoteCallManager(remoteCallManagerImpl: RemoteCallManagerImpl): RemoteCallManager

    @Binds
    abstract fun sessionManager(sessionManagerImpl: SessionManagerImpl): SessionManager

    @Binds
    abstract fun appThemeManager(appThemeManagerImpl: AppThemeManagerImpl): AppThemeManager

    @Binds
    abstract fun workManager(workManagerImpl: WorkManagerProviderImpl): WorkManagerProvider
}