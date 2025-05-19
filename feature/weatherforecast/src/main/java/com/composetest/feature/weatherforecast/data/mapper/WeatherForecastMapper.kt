package com.composetest.feature.weatherforecast.data.mapper

import com.composetest.common.extension.convertedFromUnix
import com.composetest.feature.weatherforecast.domain.model.ForecastTemperatureModel
import com.composetest.feature.weatherforecast.domain.model.WeatherForecastLocationModel
import com.composetest.feature.weatherforecast.domain.model.WeatherForecastModel
import com.composetest.feature.weatherforecast.domain.model.WeatherNowModel
import com.composetest.feature.weatherforecast.network.request.WeatherForecastRequest
import com.composetest.feature.weatherforecast.network.response.WeatherForecastResponse
import com.composetest.feature.weatherforecast.network.response.WeatherNowResponse
import javax.inject.Inject

internal class WeatherForecastMapper @Inject constructor() {

    fun mapperToModel(response: WeatherNowResponse): WeatherNowModel {
        val lastWeatherNowData = response.weatherDataList.last()
        return WeatherNowModel(
            city = response.city,
            temperature = response.temperatureData.temperature,
            iconId = lastWeatherNowData.icon,
            description = lastWeatherNowData.description
        )
    }

    fun mapperToModels(response: WeatherForecastResponse): List<WeatherForecastModel> {
        return response.dataList.map {
            WeatherForecastModel(
                dateTime = it.dateTime.convertedFromUnix,
                forecastTemperature = ForecastTemperatureModel(
                    iconId = it.weatherData.first().icon,
                    temperature = it.temperatureData.temperature,
                    minTemperature = it.temperatureData.minTemperature,
                    maxTemperature = it.temperatureData.maxTemperature
                )
            )
        }
    }

    fun mapperToRequest(model: WeatherForecastLocationModel) = WeatherForecastRequest(
        latitude = model.latitude.toString(),
        longitude = model.longitude.toString(),
        language = model.language,
        metric = model.metric
    )
}