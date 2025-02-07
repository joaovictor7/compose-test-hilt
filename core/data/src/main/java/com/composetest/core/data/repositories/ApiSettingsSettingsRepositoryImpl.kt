package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.remote.RemoteConfigDataSource
import com.composetest.core.data.mappers.ApiSettingsMapper
import com.composetest.core.data.mappers.WeatherForecastApiSettingsMapper
import com.composetest.core.domain.enums.Api
import com.composetest.core.domain.enums.remoteconfigs.ApiRemoteConfig
import com.composetest.core.domain.models.ApiSettings
import com.composetest.core.domain.models.WeatherForecastApiSettings
import com.composetest.core.domain.repositories.ApiSettingsRepository
import javax.inject.Inject

internal class ApiSettingsSettingsRepositoryImpl @Inject constructor(
    private val remoteConfigDataSource: RemoteConfigDataSource,
    private val apiSettingsMapper: ApiSettingsMapper,
    private val weatherForecastApiSettingsMapper: WeatherForecastApiSettingsMapper,
) : ApiSettingsRepository {

    override fun getApiSettings(api: Api) = runCatching {
        val json = remoteConfigDataSource.getString(api.remoteConfig.key)
        apiSettingsMapper(json)
    }.getOrDefault(ApiSettings())

    override fun getWeatherForecastApiSettings() = runCatching {
        val json = remoteConfigDataSource.getString(ApiRemoteConfig.WEATHER_FORECAST.key)
        weatherForecastApiSettingsMapper(json)
    }.getOrDefault(WeatherForecastApiSettings())
}