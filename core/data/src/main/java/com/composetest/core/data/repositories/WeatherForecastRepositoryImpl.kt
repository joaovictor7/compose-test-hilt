package com.composetest.core.data.repositories

import com.composetest.common.providers.LocaleProvider
import com.composetest.core.data.api.requests.WeatherForecastRequest
import com.composetest.core.data.datasources.OpenWeatherDataSource
import com.composetest.core.data.mappers.WeatherForecastMapper
import com.composetest.core.domain.models.weatherforecast.WeatherForecastModel
import com.composetest.core.domain.models.weatherforecast.WeatherNowModel
import com.composetest.core.domain.repositories.WeatherForecastRepository
import javax.inject.Inject

internal class WeatherForecastRepositoryImpl @Inject constructor(
    private val openWeatherDataSource: OpenWeatherDataSource,
    private val weatherForecastMapper: WeatherForecastMapper,
    private val localeProvider: LocaleProvider
) : WeatherForecastRepository {

    override suspend fun getWeatherNow(): WeatherNowModel {
        val response = openWeatherDataSource.getWeatherNow(createRequest())
        return weatherForecastMapper(response)
    }

    override suspend fun getWeatherForecasts(): List<WeatherForecastModel> {
        val response = openWeatherDataSource.getWeatherForecasts(createRequest())
        return weatherForecastMapper(response)
    }

    private fun createRequest() = WeatherForecastRequest(
        latitude = PORTO_CITY_LATITUDE,
        longitude = PORTO_CITY_LONGITUDE,
        language = localeProvider.currentLanguage,
        metric = "metric"
    )

    private companion object {
        const val PORTO_CITY_LATITUDE = "41.15"
        const val PORTO_CITY_LONGITUDE = "-8.61024"
    }
}