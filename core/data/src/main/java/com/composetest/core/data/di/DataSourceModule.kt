package com.composetest.core.data.di

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.data.datasources.remote.AuthenticationDataSource
import com.composetest.core.data.datasources.remote.AuthenticationDataSourceImpl
import com.composetest.core.data.datasources.remote.AuthenticationFakeDataSourceImpl
import com.composetest.core.data.datasources.remote.NewsApiDataSource
import com.composetest.core.data.datasources.remote.NewsApiDataSourceImpl
import com.composetest.core.data.datasources.remote.NewsApiFakeDataSourceImpl
import com.composetest.core.data.datasources.remote.OpenWeatherDataSource
import com.composetest.core.data.datasources.remote.OpenWeatherDataSourceImpl
import com.composetest.core.data.datasources.remote.OpenWeatherFakeDataSourceImpl
import com.composetest.core.data.di.qualifiers.ApiQualifier
import com.composetest.core.data.enums.Api
import com.composetest.core.data.mappers.AuthenticationMapper
import com.composetest.core.data.providers.EnvironmentInstanceProvider
import com.composetest.core.data.utils.RemoteCallUtils
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {

    @Provides
    fun authenticationDataSource(
        environmentInstanceProvider: EnvironmentInstanceProvider,
        dateTimeProvider: DateTimeProvider,
        firebaseAuth: FirebaseAuth,
        authenticationMapper: AuthenticationMapper,
        remoteCallUtils: RemoteCallUtils,
    ): AuthenticationDataSource = environmentInstanceProvider.getInstance(
        instance = AuthenticationDataSourceImpl(
            firebaseAuth = firebaseAuth,
            authenticationMapper = authenticationMapper,
            remoteCallUtils = remoteCallUtils
        ),
        fakeInstance = AuthenticationFakeDataSourceImpl(
            remoteCallUtils = remoteCallUtils,
            dateTimeProvider = dateTimeProvider
        )
    )

    @Provides
    fun openWeatherDataSource(
        environmentInstanceProvider: EnvironmentInstanceProvider,
        remoteCallUtils: RemoteCallUtils,
        @ApiQualifier(Api.OPEN_WEATHER) openWeatherApi: HttpClient
    ): OpenWeatherDataSource = environmentInstanceProvider.getInstance(
        instance = OpenWeatherDataSourceImpl(
            remoteCallUtils = remoteCallUtils,
            openWeatherApi = openWeatherApi,
        ),
        fakeInstance = OpenWeatherFakeDataSourceImpl(
            remoteCallUtils = remoteCallUtils,
        )
    )

    @Provides
    fun newsApiDataSource(
        environmentInstanceProvider: EnvironmentInstanceProvider,
        remoteCallUtils: RemoteCallUtils,
        @ApiQualifier(Api.NEWS_API) newsApi: HttpClient
    ): NewsApiDataSource = environmentInstanceProvider.getInstance(
        instance = NewsApiDataSourceImpl(
            remoteCallUtils = remoteCallUtils,
            newsApi = newsApi
        ),
        fakeInstance = NewsApiFakeDataSourceImpl(
            remoteCallUtils = remoteCallUtils
        )
    )
}