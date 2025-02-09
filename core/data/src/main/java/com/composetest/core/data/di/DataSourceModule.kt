package com.composetest.core.data.di

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.data.datasources.AuthenticationDataSource
import com.composetest.core.data.datasources.CoinDataSource
import com.composetest.core.data.datasources.NewsApiDataSource
import com.composetest.core.data.datasources.OpenWeatherDataSource
import com.composetest.core.data.datasources.local.AuthenticationFakeDataSourceImpl
import com.composetest.core.data.datasources.local.CoinDataSourceFakeImpl
import com.composetest.core.data.datasources.local.NewsApiFakeDataSourceImpl
import com.composetest.core.data.datasources.local.OpenWeatherFakeDataSourceImpl
import com.composetest.core.data.datasources.remote.AuthenticationDataSourceImpl
import com.composetest.core.data.datasources.remote.CoinDataSourceImpl
import com.composetest.core.data.datasources.remote.NewsApiDataSourceImpl
import com.composetest.core.data.datasources.remote.OpenWeatherDataSourceImpl
import com.composetest.core.data.di.qualifiers.ApiQualifier
import com.composetest.core.data.mappers.AuthenticationMapper
import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.providers.EnvironmentInstanceProvider
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.core.domain.enums.Api
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
        assetsProvider: AssetsProvider,
        firebaseAuth: FirebaseAuth,
        authenticationMapper: AuthenticationMapper,
        apiCallUtils: ApiCallUtils,
    ): AuthenticationDataSource = environmentInstanceProvider.getInstance(
        instance = AuthenticationDataSourceImpl(
            firebaseAuth = firebaseAuth,
            authenticationMapper = authenticationMapper,
            apiCallUtils = apiCallUtils
        ),
        fakeInstance = AuthenticationFakeDataSourceImpl(
            apiCallUtils = apiCallUtils,
            dateTimeProvider = dateTimeProvider,
            assetsProvider = assetsProvider
        )
    )

    @Provides
    fun openWeatherDataSource(
        environmentInstanceProvider: EnvironmentInstanceProvider,
        apiCallUtils: ApiCallUtils,
        assetsProvider: AssetsProvider,
        @ApiQualifier(Api.OPEN_WEATHER) openWeatherApi: HttpClient
    ): OpenWeatherDataSource = environmentInstanceProvider.getInstance(
        instance = OpenWeatherDataSourceImpl(
            apiCallUtils = apiCallUtils,
            openWeatherApi = openWeatherApi,
        ),
        fakeInstance = OpenWeatherFakeDataSourceImpl(
            apiCallUtils = apiCallUtils,
            assetsProvider = assetsProvider
        )
    )

    @Provides
    fun newsApiDataSource(
        environmentInstanceProvider: EnvironmentInstanceProvider,
        apiCallUtils: ApiCallUtils,
        assetsProvider: AssetsProvider,
        @ApiQualifier(Api.NEWS_API) newsApi: HttpClient
    ): NewsApiDataSource = environmentInstanceProvider.getInstance(
        instance = NewsApiDataSourceImpl(
            apiCallUtils = apiCallUtils,
            newsApi = newsApi
        ),
        fakeInstance = NewsApiFakeDataSourceImpl(
            apiCallUtils = apiCallUtils,
            assetsProvider = assetsProvider
        )
    )

    @Provides
    fun coinDataSource(
        apiCallUtils: ApiCallUtils,
        assetsProvider: AssetsProvider,
        environmentInstanceProvider: EnvironmentInstanceProvider,
        @ApiQualifier(Api.COIN_API) httpClient: HttpClient
    ): CoinDataSource = environmentInstanceProvider.getInstance(
        instance = CoinDataSourceImpl(apiCallUtils, httpClient),
        fakeInstance = CoinDataSourceFakeImpl(apiCallUtils, assetsProvider)
    )
}