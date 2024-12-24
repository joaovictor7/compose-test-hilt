package com.composetest.core.data.di

import com.composetest.core.data.managers.AppThemeManagerImpl
import com.composetest.core.data.managers.BiometricManagerImpl
import com.composetest.core.data.managers.RemoteConfigManagerImpl
import com.composetest.core.data.managers.SessionManagerImpl
import com.composetest.core.data.providers.WorkManagerProvider
import com.composetest.core.data.providers.WorkManagerProviderImpl
import com.composetest.core.domain.managers.AppThemeManager
import com.composetest.core.domain.managers.BiometricManager
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
    abstract fun sessionManager(sessionManagerImpl: SessionManagerImpl): SessionManager

    @Binds
    abstract fun appThemeManager(appThemeManagerImpl: AppThemeManagerImpl): AppThemeManager

    @Binds
    abstract fun workManager(workManagerImpl: WorkManagerProviderImpl): WorkManagerProvider

    @Binds
    abstract fun remoteConfigManager(remoteConfigManagerImpl: RemoteConfigManagerImpl): RemoteConfigManager

    @Binds
    abstract fun biometricManager(biometricManagerImpl: BiometricManagerImpl): BiometricManager
}