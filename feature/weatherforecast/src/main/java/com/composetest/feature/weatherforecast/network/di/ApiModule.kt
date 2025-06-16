package com.composetest.feature.weatherforecast.network.di

import com.composetest.core.domain.provider.BuildConfigProvider
import com.composetest.core.domain.repository.RemoteConfigRepository
import com.composetest.core.network.HttpClientBuilder
import com.composetest.core.network.di.qualifier.ApiQualifier
import com.composetest.feature.weatherforecast.network.OpenWeatherApi
import com.composetest.feature.weatherforecast.network.api.Api
import com.composetest.feature.weatherforecast.network.enums.ApiKeyRemoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @ApiQualifier(Api.OPEN_WEATHER_API)
    fun openWeatherApi(
        remoteConfigRepository: RemoteConfigRepository,
        buildConfigProvider: BuildConfigProvider,
    ): HttpClient = HttpClientBuilder.build(
        OpenWeatherApi(
            apiId = remoteConfigRepository.getString(ApiKeyRemoteConfig.OPEN_WEATHER_API),
            url = buildConfigProvider.buildConfigFields.openWeatherApiHost,
        )
    )
}