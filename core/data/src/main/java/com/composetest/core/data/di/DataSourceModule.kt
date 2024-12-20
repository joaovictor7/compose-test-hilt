package com.composetest.core.data.di

import com.composetest.core.data.datasources.local.AppThemeDataSource
import com.composetest.core.data.datasources.local.AppThemeDataSourceImpl
import com.composetest.core.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.datasources.local.PreferenceDataSourceImpl
import com.composetest.core.data.datasources.local.SessionDataSource
import com.composetest.core.data.datasources.local.SessionDataSourceImpl
import com.composetest.core.data.datasources.local.UserDataSource
import com.composetest.core.data.datasources.local.UserDataSourceImpl
import com.composetest.core.data.datasources.remote.AuthenticationDataSource
import com.composetest.core.data.datasources.remote.AuthenticationDataSourceImpl
import com.composetest.core.data.datasources.remote.AuthenticationFakeDataSourceImpl
import com.composetest.core.data.datasources.remote.AnalyticsDataSource
import com.composetest.core.data.datasources.remote.AnalyticsDataSourceImpl
import com.composetest.core.data.datasources.remote.RemoteConfigDataSource
import com.composetest.core.data.datasources.remote.RemoteConfigDataSourceImpl
import com.composetest.core.data.mappers.AuthenticationMapper
import com.composetest.core.data.providers.FakeInstanceProvider
import com.composetest.core.data.utils.RemoteCallUtils
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceBindsModule {

    @Binds
    abstract fun firebaseAnalyticsDataSource(
        firebaseAnalyticsDataSourceImpl: AnalyticsDataSourceImpl
    ): AnalyticsDataSource

    @Binds
    abstract fun firebaseRemoteConfigsDataSource(
        firebaseRemoteConfigDataSourceImpl: RemoteConfigDataSourceImpl
    ): RemoteConfigDataSource

    @Binds
    abstract fun preferenceDataSource(
        preferenceDataSourceImpl: PreferenceDataSourceImpl
    ): PreferenceDataSource

    @Binds
    abstract fun userDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource

    @Binds
    abstract fun sessionDataSource(sessionDataSourceImpl: SessionDataSourceImpl): SessionDataSource

    @Binds
    abstract fun appThemeDataSource(appThemeDataSourceImpl: AppThemeDataSourceImpl): AppThemeDataSource
}

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceProvidesModule {

    @Provides
    fun authenticationDataSource(
        fakeInstanceProvider: FakeInstanceProvider,
        firebaseAuth: FirebaseAuth,
        authenticationMapper: AuthenticationMapper,
        remoteCallUtils: RemoteCallUtils
    ): AuthenticationDataSource = fakeInstanceProvider.getInstance(
        instance = AuthenticationDataSourceImpl(
            firebaseAuth = firebaseAuth,
            authenticationMapper = authenticationMapper,
            remoteCallUtils = remoteCallUtils
        ),
        fakeInstance = AuthenticationFakeDataSourceImpl(
            remoteCallUtils = remoteCallUtils
        )
    )
}