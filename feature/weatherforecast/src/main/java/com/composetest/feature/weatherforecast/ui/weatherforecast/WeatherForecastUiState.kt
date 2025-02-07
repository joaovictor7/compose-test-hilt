package com.composetest.feature.weatherforecast.ui.weatherforecast

import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.designsystem.utils.getCommonSimpleDialogErrorParam
import com.composetest.core.domain.models.weatherforecast.TodayWeatherForecastModel
import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.weatherforecast.enums.WeatherForecastScreenStatus
import com.composetest.feature.weatherforecast.enums.WeatherForecastStatus
import com.composetest.feature.weatherforecast.models.FutureWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.models.TodayWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.models.WeatherNowScreenModel

internal data class WeatherForecastUiState(
    val screenStatus: WeatherForecastScreenStatus = WeatherForecastScreenStatus.INITIAL,
    val weatherNow: WeatherNowScreenModel = WeatherNowScreenModel(),
    val todayWeatherForecast: TodayWeatherForecastScreenModel? = TodayWeatherForecastScreenModel(),
    val futureWeatherForecasts: List<FutureWeatherForecastScreenModel> = emptyList(),
    val simpleDialogParam: SimpleDialogParam? = null,
    val weatherNowStatus: WeatherForecastStatus = WeatherForecastStatus.LOADING,
    val weatherForecastsStatus: WeatherForecastStatus = WeatherForecastStatus.LOADING,
) : BaseUiState {

    val showFullScreenMsg
        get() = screenStatus in listOf(
            WeatherForecastScreenStatus.TRY_AGAIN,
            WeatherForecastScreenStatus.PERMISSION_NOT_GRANTED
        ) || (weatherNowStatus == WeatherForecastStatus.ERROR && weatherForecastsStatus == WeatherForecastStatus.ERROR)

    fun setScreenStatus(screenStatus: WeatherForecastScreenStatus) =
        copy(screenStatus = screenStatus)

    fun setWeatherNow(weatherNowModel: WeatherNowScreenModel) = copy(
        weatherNowStatus = WeatherForecastStatus.SUCCESS,
        weatherNow = weatherNowModel,
    )

    fun setWeatherForecasts(
        todayWeatherForecast: TodayWeatherForecastModel?,
        futureWeatherForecastScreens: List<FutureWeatherForecastScreenModel>
    ) = copy(
        weatherForecastsStatus = WeatherForecastStatus.SUCCESS,
        todayWeatherForecast = todayWeatherForecast?.let {
            TodayWeatherForecastScreenModel(
                minTemperature = todayWeatherForecast.minTemperature,
                maxTemperature = todayWeatherForecast.maxTemperature,
                temperatures = todayWeatherForecast.temperatures
            )
        },
        futureWeatherForecasts = futureWeatherForecastScreens
    )

    fun setLocationError(error: Throwable?) = copy(
        screenStatus = WeatherForecastScreenStatus.TRY_AGAIN,
        simpleDialogParam = getCommonSimpleDialogErrorParam(error)
    )

    fun setWeatherNowError(error: Throwable?) = copy(
        weatherNowStatus = WeatherForecastStatus.ERROR,
        simpleDialogParam = getCommonSimpleDialogErrorParam(error)
    )

    fun setWeatherForecastsError(error: Throwable?) = copy(
        weatherForecastsStatus = WeatherForecastStatus.ERROR,
        simpleDialogParam = getCommonSimpleDialogErrorParam(error)
    )

    fun dismissSimpleDialog() = copy(simpleDialogParam = null)
}
