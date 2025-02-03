package com.composetest.feature.weatherforecast.ui.weatherforecast

import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.domain.models.weatherforecast.TodayWeatherForecastModel
import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.weatherforecast.R
import com.composetest.feature.weatherforecast.enums.WeatherForecastScreenStatus
import com.composetest.feature.weatherforecast.models.FutureWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.models.TodayWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.models.WeatherNowScreenModel

internal data class WeatherForecastUiState(
    val screenStatus: WeatherForecastScreenStatus = WeatherForecastScreenStatus.INITIAL,
    val weatherNow: WeatherNowScreenModel = WeatherNowScreenModel(),
    val todayWeatherForecast: TodayWeatherForecastScreenModel? = TodayWeatherForecastScreenModel(),
    val futureWeatherForecasts: List<FutureWeatherForecastScreenModel> = emptyList(),
    val simpleDialogParam: SimpleDialogParam? = null,
    val forecastIsLoading: Boolean = false,
    override val isLoading: Boolean = false
) : BaseUiState {

    val screenIsInitial get() = screenStatus == WeatherForecastScreenStatus.INITIAL
    val screenIsReady get() = screenStatus == WeatherForecastScreenStatus.READY
    val screenFullMessageId get() = when (screenStatus) {
        WeatherForecastScreenStatus.TRY_AGAIN -> R.string.weather_forecast_try_again
        WeatherForecastScreenStatus.PERMISSION_NOT_GRANTED -> R.string.weather_forecast_required_permission_msg
        else -> null
    }

    fun setScreenStatus(screenStatus: WeatherForecastScreenStatus) =
        copy(screenStatus = screenStatus)

    fun setWeatherNow(weatherNowModel: WeatherNowScreenModel) = copy(
        weatherNow = weatherNowModel
    )

    fun setWeatherForecasts(
        todayWeatherForecast: TodayWeatherForecastModel?,
        futureWeatherForecastScreens: List<FutureWeatherForecastScreenModel>
    ) = copy(
        todayWeatherForecast = todayWeatherForecast?.let {
            TodayWeatherForecastScreenModel(
                minTemperature = todayWeatherForecast.minTemperature,
                maxTemperature = todayWeatherForecast.maxTemperature,
                temperatures = todayWeatherForecast.temperatures
            )
        },
        futureWeatherForecasts = futureWeatherForecastScreens
    )

    fun setLoading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun setSimpleAlertDialog(simpleDialogParam: SimpleDialogParam?) =
        copy(simpleDialogParam = simpleDialogParam)
}
