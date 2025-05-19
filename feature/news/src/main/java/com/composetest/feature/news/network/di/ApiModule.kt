package com.composetest.feature.news.network.di

import com.composetest.core.domain.provider.BuildConfigProvider
import com.composetest.core.domain.repository.RemoteConfigRepository
import com.composetest.core.network.HttpClientBuilder
import com.composetest.core.network.di.qualifier.ApiQualifier
import com.composetest.feature.news.network.NewsApiSetting
import com.composetest.feature.news.network.api.Api
import com.composetest.feature.news.network.enums.ApiKeyRemoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @ApiQualifier(Api.NEWS_API)
    fun newsApi(
        remoteConfigRepository: RemoteConfigRepository,
        buildConfigProvider: BuildConfigProvider
    ): HttpClient = HttpClientBuilder.build(
        NewsApiSetting(
            apiKey = remoteConfigRepository.getString(ApiKeyRemoteConfig.NEWS_API),
            url = buildConfigProvider.buildConfigFields.newsApiHost,
        )
    )
}