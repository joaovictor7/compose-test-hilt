package com.composetest.core.data.di

import com.composetest.core.data.api.ApiSetting
import com.composetest.core.data.api.HttpClientBuilder
import com.composetest.core.data.di.qualifiers.ApiQualifier
import com.composetest.core.data.enums.Api
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
        val newsApiSettings = apiSettingsRepository.getNewsApiSettings()
        return HttpClientBuilder.build(
            ApiSetting.NewsApi(
                apiKey = newsApiSettings.apiKey,
                url = newsApiSettings.url,
                country = "us",
            )
        )
    }

    @Provides
    @ApiQualifier(Api.OPEN_WEATHER)
    fun openWeatherApi(apiSettingsRepository: ApiSettingsRepository): HttpClient {
        val weatherForecastApiSettings = apiSettingsRepository.getWeatherForecastApiSettings()
        return HttpClientBuilder.build(
            ApiSetting.OpenWeatherApi(
                apiId = weatherForecastApiSettings.apiId,
                url = weatherForecastApiSettings.url,
            )
        )
    }
}