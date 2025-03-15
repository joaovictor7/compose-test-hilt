package com.composetest.feature.weatherforecast.presenter.ui

import com.composetest.core.designsystem.enums.topbar.TopBarAction
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.feature.weatherforecast.domain.models.TodayWeatherForecastModel
import com.composetest.feature.weatherforecast.presenter.enums.WeatherForecastScreenStatus
import com.composetest.feature.weatherforecast.presenter.enums.WeatherForecastStatus
import com.composetest.feature.weatherforecast.presenter.models.FutureWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.presenter.models.TodayWeatherForecastScreenModel
import com.composetest.feature.weatherforecast.presenter.models.WeatherNowScreenModel

internal data class WeatherForecastUiState(
    val screenStatus: WeatherForecastScreenStatus = WeatherForecastScreenStatus.INITIAL,
    val weatherNow: WeatherNowScreenModel = WeatherNowScreenModel(),
    val todayWeatherForecast: TodayWeatherForecastScreenModel? = TodayWeatherForecastScreenModel(),
    val futureWeatherForecasts: List<FutureWeatherForecastScreenModel> = emptyList(),
    val simpleDialogParam: SimpleDialogParam? = null,
    val weatherNowStatus: WeatherForecastStatus = WeatherForecastStatus.LOADING,
    val weatherForecastsStatus: WeatherForecastStatus = WeatherForecastStatus.LOADING,
) {

    val refreshButton
        get() = if (!showFullScreenMsg && WeatherForecastStatus.LOADING !in listOf(
                weatherNowStatus,
                weatherForecastsStatus
            )
        ) listOf(TopBarAction.REFRESH) else null

    val showFullScreenMsg
        get() = screenStatus in listOf(
            WeatherForecastScreenStatus.TRY_AGAIN,
            WeatherForecastScreenStatus.PERMISSION_NOT_GRANTED
        ) || (weatherNowStatus == WeatherForecastStatus.ERROR && weatherForecastsStatus == WeatherForecastStatus.ERROR)

    val isPermissionNotGranted get() =
        screenStatus == WeatherForecastScreenStatus.PERMISSION_NOT_GRANTED

    fun setScreenStatus(screenStatus: WeatherForecastScreenStatus) =
        copy(screenStatus = screenStatus)

    fun setWeatherNow(weatherNowModel: WeatherNowScreenModel) = copy(
        weatherNowStatus = WeatherForecastStatus.READY,
        weatherNow = weatherNowModel,
    )

    fun setWeatherForecasts(
        todayWeatherForecast: TodayWeatherForecastModel?,
        futureWeatherForecastScreens: List<FutureWeatherForecastScreenModel>
    ) = copy(
        weatherForecastsStatus = WeatherForecastStatus.READY,
        todayWeatherForecast = todayWeatherForecast?.let {
            TodayWeatherForecastScreenModel(
                minTemperature = todayWeatherForecast.minTemperature,
                maxTemperature = todayWeatherForecast.maxTemperature,
                temperatures = todayWeatherForecast.temperatures
            )
        },
        futureWeatherForecasts = futureWeatherForecastScreens
    )

    fun setLocationError(simpleDialogParam: SimpleDialogParam?) = copy(
        screenStatus = WeatherForecastScreenStatus.TRY_AGAIN,
        simpleDialogParam = simpleDialogParam,
    )

    fun setWeatherNowError(
        weatherNowStatus: WeatherForecastStatus,
        simpleDialogParam: SimpleDialogParam?
    ) = copy(
        weatherNowStatus = weatherNowStatus,
        simpleDialogParam = simpleDialogParam
    )

    fun setWeatherForecastsError(
        weatherForecastsStatus: WeatherForecastStatus,
        simpleDialogParam: SimpleDialogParam?
    ) = copy(
        weatherForecastsStatus = weatherForecastsStatus,
        simpleDialogParam = simpleDialogParam
    )

    fun dismissSimpleDialog() = copy(simpleDialogParam = null)
}
