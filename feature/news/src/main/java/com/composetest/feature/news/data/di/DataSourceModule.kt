package com.composetest.feature.news.data.di

import com.composetest.core.data.providers.AssetsProvider
import com.composetest.core.data.providers.EnvironmentInstanceProvider
import com.composetest.core.data.utils.ApiCallUtils
import com.composetest.core.network.di.qualifiers.ApiQualifier
import com.composetest.core.network.enums.Api
import com.composetest.feature.news.data.datasources.NewsApiDataSource
import com.composetest.feature.news.data.datasources.local.NewsApiFakeDataSourceImpl
import com.composetest.feature.news.data.datasources.remote.NewsApiDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {

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