package com.composetest.core.data.di

import com.composetest.core.data.api.ApiSetting
import com.composetest.core.data.api.HttpClientBuilder
import com.composetest.core.data.di.qualifiers.ApiQualifier
import com.composetest.core.data.repositories.RemoteConfigRepository
import com.composetest.core.domain.enums.Api
import com.composetest.core.domain.enums.remoteconfigs.ApiKeyRemoteConfig
import com.composetest.core.domain.providers.BuildConfigProvider
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
        ApiSetting.NewsApi(
            apiKey = remoteConfigRepository.getString(ApiKeyRemoteConfig.NEWS_API.key),
            url = buildConfigProvider.buildConfigFields.newsApiHost,
        )
    )

    @Provides
    @ApiQualifier(Api.OPEN_WEATHER)
    fun openWeatherApi(
        remoteConfigRepository: RemoteConfigRepository,
        buildConfigProvider: BuildConfigProvider
    ): HttpClient = HttpClientBuilder.build(
        ApiSetting.OpenWeatherApi(
            apiId = remoteConfigRepository.getString(ApiKeyRemoteConfig.OPEN_WEATHER_API.key),
            url = buildConfigProvider.buildConfigFields.openWeatherApiHost,
        )
    )

    @Provides
    @ApiQualifier(Api.COIN_API)
    fun coinApi(
        remoteConfigRepository: RemoteConfigRepository,
        buildConfigProvider: BuildConfigProvider
    ): HttpClient = HttpClientBuilder.build(
        ApiSetting.CoinApi(
            apiKey = remoteConfigRepository.getString(ApiKeyRemoteConfig.COIN_API.key),
            url = buildConfigProvider.buildConfigFields.coinApiHost,
        )
    )
}