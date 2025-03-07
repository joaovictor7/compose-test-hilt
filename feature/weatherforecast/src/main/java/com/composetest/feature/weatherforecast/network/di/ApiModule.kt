package com.composetest.feature.weatherforecast.network.di

import com.composetest.core.domain.enums.remoteconfigs.ApiKeyRemoteConfig
import com.composetest.core.domain.providers.BuildConfigProvider
import com.composetest.core.domain.repositories.RemoteConfigRepository
import com.composetest.core.network.HttpClientBuilder
import com.composetest.core.network.di.qualifiers.ApiQualifier
import com.composetest.core.network.enums.Api
import com.composetest.feature.weatherforecast.network.OpenWeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @ApiQualifier(Api.OPEN_WEATHER)
    fun openWeatherApi(
        remoteConfigRepository: RemoteConfigRepository,
        buildConfigProvider: BuildConfigProvider
    ): HttpClient = HttpClientBuilder.build(
        OpenWeatherApi(
            apiId = remoteConfigRepository.getString(ApiKeyRemoteConfig.OPEN_WEATHER_API.key),
            url = buildConfigProvider.buildConfigFields.openWeatherApiHost,
        )
    )
}