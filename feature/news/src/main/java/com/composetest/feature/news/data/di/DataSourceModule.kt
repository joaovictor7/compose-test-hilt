package com.composetest.feature.news.data.di

import com.composetest.core.data.provider.AssetsProvider
import com.composetest.core.data.provider.EnvironmentInstanceProvider
import com.composetest.core.network.di.qualifier.ApiQualifier
import com.composetest.core.network.util.ApiCallUtils
import com.composetest.feature.news.data.datasource.NewsApiDataSource
import com.composetest.feature.news.data.datasource.local.NewsApiFakeDataSourceImpl
import com.composetest.feature.news.data.datasource.remote.NewsApiDataSourceImpl
import com.composetest.feature.news.network.api.Api
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