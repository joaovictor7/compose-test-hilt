package com.composetest.core.data.di

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.data.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.data.datasources.local.PreferenceDataSourceImpl
import com.composetest.core.data.data.datasources.local.SessionDataSource
import com.composetest.core.data.data.datasources.local.SessionDataSourceImpl
import com.composetest.core.data.data.datasources.local.UserDataSource
import com.composetest.core.data.data.datasources.local.UserDataSourceImpl
import com.composetest.core.data.data.datasources.remote.AuthenticationDataSource
import com.composetest.core.data.data.datasources.remote.AuthenticationDataSourceImpl
import com.composetest.core.data.data.datasources.remote.AuthenticationFakeDataSourceImpl
import com.composetest.core.data.data.datasources.remote.FirebaseAnalyticsDataSource
import com.composetest.core.data.data.datasources.remote.FirebaseAnalyticsDataSourceImpl
import com.composetest.core.data.di.qualifiers.Api
import com.composetest.core.data.enums.NetworkApi
import com.composetest.core.data.managers.RemoteCallManager
import com.composetest.core.data.providers.FakeInstanceProvider
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
    abstract fun preferenceDataSource(
        preferenceDataSourceImpl: PreferenceDataSourceImpl
    ): PreferenceDataSource

    @Binds
    abstract fun userDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource

    @Binds
    abstract fun sessionDataSource(sessionDataSourceImpl: SessionDataSourceImpl): SessionDataSource
}

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceProvidesModule {

    @Provides
    fun authenticationDataSource(
        fakeInstanceProvider: FakeInstanceProvider,
        @Api(NetworkApi.BFF) bffApi: HttpClient,
        dateTimeProvider: DateTimeProvider,
        safeRemoteCallManager: RemoteCallManager
    ): AuthenticationDataSource = fakeInstanceProvider.getInstance(
        instance = AuthenticationDataSourceImpl(
            bffApi = bffApi,
            safeRemoteCallManager = safeRemoteCallManager
        ),
        fakeInstance = AuthenticationFakeDataSourceImpl(
            dateTimeProvider = dateTimeProvider,
            safeRemoteCallManager = safeRemoteCallManager
        )
    )
}