package com.composetest.feature.weatherforecast.presenter.mappers

import com.composetest.feature.weatherforecast.domain.models.FutureWeatherForecastModel
import com.composetest.core.domain.providers.BuildConfigProvider
import com.composetest.feature.weatherforecast.presenter.models.FutureDailyWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.presenter.models.FutureWeatherForecastScreenModel
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal class FutureWeatherForecastScreenModelsMapper @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) {

    private val dateToHourFormatter = DateTimeFormatter.ofPattern("HH")
    private val dateToStringFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")

    fun mapperToModels(
        futureWeatherForecastModel: List<FutureWeatherForecastModel>
    ) = futureWeatherForecastModel.map { futureWeatherForecast ->
        FutureWeatherForecastScreenModel(
            day = futureWeatherForecast.date.format(dateToStringFormatter),
            futureDailyWeatherForecasts = futureWeatherForecast.dailyWeatherForecasts.map {
                FutureDailyWeatherForecastScreenModel(
                    hour = "${it.dateTime.format(dateToHourFormatter)}h",
                    temperature = "${it.temperature.toInt()}º",
                    iconUrl = buildConfigProvider.buildConfigFields.openWeatherIconHost.format(
                        it.iconId
                    )
                )
            }
        )
    }
}