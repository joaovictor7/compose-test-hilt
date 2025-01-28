package com.composetest.core.data.di

import com.composetest.core.data.api.ApiSetting
import com.composetest.core.data.api.HttpClientBuilder
import com.composetest.core.data.di.qualifiers.ApiQualifier
import com.composetest.core.domain.enums.Api
import com.composetest.core.domain.repositories.ApiSettingsRepository
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
    fun newsApi(apiSettingsRepository: ApiSettingsRepository): HttpClient {
        val apiSettings = apiSettingsRepository.getApiSettings(Api.NEWS_API)
        return HttpClientBuilder.build(
            ApiSetting.NewsApi(
                apiKey = apiSettings.apiKey,
                url = apiSettings.url,
            )
        )
    }

    @Provides
    @ApiQualifier(Api.OPEN_WEATHER)
    fun openWeatherApi(apiSettingsRepository: ApiSettingsRepository): HttpClient {
        val weatherForecastApiSettings = apiSettingsRepository.getWeatherForecastApiSettings()
        return HttpClientBuilder.build(
            ApiSetting.OpenWeatherApi(
                apiId = weatherForecastApiSettings.apiSettings.apiKey,
                url = weatherForecastApiSettings.apiSettings.url,
            )
        )
    }

    @Provides
    @ApiQualifier(Api.COIN_API)
    fun coinApi(apiSettingsRepository: ApiSettingsRepository): HttpClient {
        val apiSettings = apiSettingsRepository.getApiSettings(Api.COIN_API)
        return HttpClientBuilder.build(
            ApiSetting.CoinApi(
                apiKey = apiSettings.apiKey,
                url = apiSettings.url,
            )
        )
    }
}