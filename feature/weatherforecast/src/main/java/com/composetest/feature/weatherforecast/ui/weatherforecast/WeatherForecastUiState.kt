package com.composetest.feature.weatherforecast.ui.weatherforecast

import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.domain.models.weatherforecast.TodayWeatherForecastModel
import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.weatherforecast.models.FutureWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.models.TodayWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.models.WeatherNowScreenModel

internal data class WeatherForecastUiState(
    val weatherNowModel: WeatherNowScreenModel = WeatherNowScreenModel(),
    val todayWeatherForecast: TodayWeatherForecastScreenModel? = TodayWeatherForecastScreenModel(),
    val futureWeatherForecasts: List<FutureWeatherForecastScreenModel> = emptyList(),
    val simpleDialogParam: SimpleDialogParam? = null,
    override val isLoading: Boolean = false
) : BaseUiState {

    fun setWeatherNow(weatherNowModel: WeatherNowScreenModel) = copy(
        weatherNowModel = weatherNowModel
    )

    fun setWeatherForecasts(
        todayWeatherForecastModel: TodayWeatherForecastModel?,
        futureWeatherForecastScreenModels: List<FutureWeatherForecastScreenModel>
    ) = copy(
        todayWeatherForecast = todayWeatherForecastModel?.let {
            TodayWeatherForecastScreenModel(
                minTemperature = todayWeatherForecastModel.minTemperature,
                maxTemperature = todayWeatherForecastModel.maxTemperature,
                temperatures = todayWeatherForecastModel.temperatures
            )
        },
        futureWeatherForecasts = futureWeatherForecastScreenModels
    )

    fun setLoading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun setSimpleAlertDialog(simpleDialogParam: SimpleDialogParam?) =
        copy(simpleDialogParam = simpleDialogParam)
}
