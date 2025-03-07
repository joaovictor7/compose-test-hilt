package com.composetest.core.data.di

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.data.datasources.AuthenticationDataSource
import com.composetest.core.data.datasources.NewsApiDataSource
import com.composetest.core.data.datasources.local.AuthenticationFakeDataSourceImpl
import com.composetest.core.data.datasources.local.NewsApiFakeDataSourceImpl
import com.composetest.core.data.datasources.remote.AuthenticationDataSourceImpl
import com.composetest.core.data.datasources.remote.NewsApiDataSourceImpl
import com.composetest.core.data.mappers.AuthenticationMapper
import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.providers.AssetsProviderImpl
import com.composetest.core.data.providers.EnvironmentInstanceProvider
import com.composetest.core.data.providers.EnvironmentInstanceProviderImpl
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.core.network.di.qualifiers.ApiQualifier
import com.composetest.core.network.enums.Api
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
        environmentInstanceProvider: EnvironmentInstanceProviderImpl,
        dateTimeProvider: DateTimeProvider,
        assetsProvider: AssetsProviderImpl,
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
}