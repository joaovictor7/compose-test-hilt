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
import com.composetest.core.data.datasources.remote.FirebaseAnalyticsDataSource
import com.composetest.core.data.datasources.remote.FirebaseAnalyticsDataSourceImpl
import com.composetest.core.data.datasources.remote.FirebaseRemoteConfigDataSource
import com.composetest.core.data.datasources.remote.FirebaseRemoteConfigDataSourceImpl
import com.composetest.core.data.di.qualifiers.ApiQualifier
import com.composetest.core.data.enums.Api
import com.composetest.core.data.providers.FakeInstanceProvider
import com.composetest.core.data.utils.RemoteCallUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceBindsModule {

    @Binds
    abstract fun firebaseAnalyticsDataSource(
        firebaseAnalyticsDataSourceImpl: FirebaseAnalyticsDataSourceImpl
    ): FirebaseAnalyticsDataSource

    @Binds
    abstract fun firebaseRemoteConfigsDataSource(
        firebaseRemoteConfigDataSourceImpl: FirebaseRemoteConfigDataSourceImpl
    ): FirebaseRemoteConfigDataSource

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
        @ApiQualifier(Api.BFF) bffApi: HttpClient,
        remoteCallUtils: RemoteCallUtils
    ): AuthenticationDataSource = fakeInstanceProvider.getInstance(
        instance = AuthenticationDataSourceImpl(
            bffApi = bffApi,
            remoteCallUtils = remoteCallUtils
        ),
        fakeInstance = AuthenticationFakeDataSourceImpl(
            remoteCallUtils = remoteCallUtils
        )
    )
}