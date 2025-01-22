package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.remote.RemoteConfigDataSource
import com.composetest.core.data.enums.DataRemoteConfig
import com.composetest.core.data.mappers.NewsApiSettingsMapper
import com.composetest.core.data.mappers.WeatherForecastApiSettingsMapper
import com.composetest.core.domain.models.NewsApiSettings
import com.composetest.core.domain.models.WeatherForecastApiSettings
import com.composetest.core.domain.repositories.ApiSettingsRepository
import javax.inject.Inject

internal class ApiSettingsSettingsRepositoryImpl @Inject constructor(
    private val remoteConfigDataSource: RemoteConfigDataSource,
    private val newsApiSettingsMapper: NewsApiSettingsMapper,
    private val weatherForecastApiSettingsMapper: WeatherForecastApiSettingsMapper,
) : ApiSettingsRepository {

    override fun getNewsApiSettings(): NewsApiSettings {
        val json = remoteConfigDataSource.getString(DataRemoteConfig.API_NEWS_API.key)
        return newsApiSettingsMapper(json)
    }

    override fun getWeatherForecastApiSettings(): WeatherForecastApiSettings {
        val json = remoteConfigDataSource.getString(DataRemoteConfig.API_WEATHER_FORECAST.key)
        return weatherForecastApiSettingsMapper(json)
    }
}