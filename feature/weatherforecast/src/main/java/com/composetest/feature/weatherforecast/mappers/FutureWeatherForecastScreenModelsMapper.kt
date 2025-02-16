package com.composetest.feature.weatherforecast.mappers

import com.composetest.core.domain.models.weatherforecast.FutureWeatherForecastModel
import com.composetest.core.domain.providers.BuildConfigProvider
import com.composetest.feature.weatherforecast.models.FutureDailyWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.models.FutureWeatherForecastScreenModel
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal class FutureWeatherForecastScreenModelsMapper @Inject constructor(
    private val buildConfigProvider: BuildConfigProvider
) {

    private val dateToHourFormatter = DateTimeFormatter.ofPattern("HH")
    private val dateToStringFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")

    operator fun invoke(
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