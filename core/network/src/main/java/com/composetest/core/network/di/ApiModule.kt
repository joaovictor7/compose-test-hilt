package com.composetest.core.network.di

import com.composetest.core.domain.enums.remoteconfigs.ApiKeyRemoteConfig
import com.composetest.core.domain.providers.BuildConfigProvider
import com.composetest.core.domain.repositories.RemoteConfigRepository
import com.composetest.core.network.ApiSetting
import com.composetest.core.network.HttpClientBuilder
import com.composetest.core.network.di.qualifiers.ApiQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @ApiQualifier.NewsApi
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
    @ApiQualifier.OpenWeather
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
    @ApiQualifier.CoinApi
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