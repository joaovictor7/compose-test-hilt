package com.composetest.feature.weatherforecast.network.di

import com.composetest.core.domain.providers.BuildConfigProvider
import com.composetest.core.domain.repositories.RemoteConfigRepository
import com.composetest.core.network.HttpClientBuilder
import com.composetest.core.network.di.qualifiers.ApiQualifier
import com.composetest.feature.weatherforecast.network.OpenWeatherApi
import com.composetest.feature.weatherforecast.network.constants.ApiConstants
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
    @ApiQualifier(ApiConstants.OPEN_WEATHER_API)
    fun openWeatherApi(
        remoteConfigRepository: RemoteConfigRepository,
        buildConfigProvider: BuildConfigProvider
    ): HttpClient = HttpClientBuilder.build(
        OpenWeatherApi(
            apiId = remoteConfigRepository.getString(ApiKeyRemoteConfig.OPEN_WEATHER_API),
            url = buildConfigProvider.buildConfigFields.openWeatherApiHost,
        )
    )
}