package com.composetest.feature.weatherforecast.ui.weatherforecast

import android.location.Location
import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.enums.ScreensAnalytic
import com.composetest.core.analytic.events.CommonAnalyticEvent
import com.composetest.core.designsystem.components.dialogs.CommonSimpleDialog
import com.composetest.core.designsystem.utils.getCommonSimpleDialogErrorParam
import com.composetest.core.domain.usecases.weatherforecast.GeTodayWeatherForecastUseCase
import com.composetest.core.domain.usecases.weatherforecast.GetFutureWeatherForecastUseCase
import com.composetest.core.domain.usecases.weatherforecast.GetWeatherForecastsUseCase
import com.composetest.core.domain.usecases.weatherforecast.GetWeatherNowUseCase
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.enums.Permission
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.providers.LocationProvider
import com.composetest.core.ui.providers.PermissionProvider
import com.composetest.core.ui.utils.AsyncTaskUtils
import com.composetest.feature.weatherforecast.enums.WeatherForecastScreenStatus
import com.composetest.feature.weatherforecast.enums.WeatherForecastStatus
import com.composetest.feature.weatherforecast.mappers.FutureWeatherForecastScreenModelsMapper
import com.composetest.feature.weatherforecast.mappers.WeatherNowScreenModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class WeatherForecastViewModel @Inject constructor(
    private val getWeatherNowUseCase: GetWeatherNowUseCase,
    private val getWeatherForecastsUseCase: GetWeatherForecastsUseCase,
    private val getTodayWeatherForecastUseCase: GeTodayWeatherForecastUseCase,
    private val getFutureWeatherForecastsUseCase: GetFutureWeatherForecastUseCase,
    private val weatherNowScreenModelMapper: WeatherNowScreenModelMapper,
    private val futureWeatherForecastScreenModelsMapper: FutureWeatherForecastScreenModelsMapper,
    private val locationProvider: LocationProvider,
    private val permissionProvider: PermissionProvider,
    private val analyticSender: AnalyticSender,
    @AsyncTaskUtilsQualifier(ScreensAnalytic.WEATHER_FORECAST) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<WeatherForecastUiState>, UiEvent<WeatherForecastUiEvent>,
    WeatherForecastCommandReceiver {

    override val commandReceiver = this

    private var location: Location? = null
    private var weatherForecastNowWasGet = false
    private var weatherForecastsWasGet = false

    private val _uiState = MutableStateFlow(WeatherForecastUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<WeatherForecastUiEvent>(replay = 1)
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        sendOpenScreenAnalytic()
        checkPermissions()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ScreensAnalytic.WEATHER_FORECAST))
        }
    }

    override fun checkPermissionsResult() {
        if (uiState.value.screenStatus == WeatherForecastScreenStatus.READY) return
        if (permissionProvider.somePermissionIsGranted(Permission.localization)) {
            _uiState.update { it.setScreenStatus(WeatherForecastScreenStatus.READY) }
            getLocationAndWeatherForecastsData()
        } else {
            _uiState.update { it.setScreenStatus(WeatherForecastScreenStatus.PERMISSION_NOT_GRANTED) }
        }
    }

    override fun getLocationAndWeatherForecastsData() {
        _uiState.update { it.setScreenLoading() }
        asyncTaskUtils.runAsyncTask(
            coroutineScope = viewModelScope,
            onError = ::handleLocationError
        ) {
            location = locationProvider.getLastLocation()
            location?.let {
                getWeatherNow(it)
                getWeatherForecastsNow(it)
            }
        }
    }

    override fun getWeatherForecastNowData() {
        location?.let { location ->
            _uiState.update { it.setWeatherNowStatus(WeatherForecastStatus.LOADING) }
            getWeatherNow(location)
        }
    }

    override fun getWeatherForecastsData() {
        location?.let { location ->
            _uiState.update { it.setWeatherForecastsStatus(WeatherForecastStatus.LOADING) }
            getWeatherForecastsNow(location)
        }
    }

    override fun dismissSimpleDialog() {
        _uiState.update { it.dismissSimpleDialog() }
    }

    private fun checkPermissions() {
        if (!permissionProvider.permissionIsGranted(Permission.localization)) {
            _uiEvent.emitEvent(WeatherForecastUiEvent.LaunchPermissionRequest)
        }
    }

    private fun getWeatherNow(location: Location) = asyncTaskUtils.runAsyncTask(
        coroutineScope = viewModelScope,
        onError = ::handleWeatherForecastNowError
    ) {
        val weatherNowForecast = getWeatherNowUseCase(location.latitude, location.longitude)
        _uiState.update {
            it.setWeatherNow(weatherNowScreenModelMapper.mapperToModel(weatherNowForecast))
        }
        weatherForecastNowWasGet = true
    }

    private fun getWeatherForecastsNow(location: Location) = asyncTaskUtils.runAsyncTask(
        coroutineScope = viewModelScope,
        onError = ::handleWeatherForecastsError
    ) {
        val weatherForecast =
            getWeatherForecastsUseCase(location.latitude, location.longitude)
        val todayWeatherForecast = getTodayWeatherForecastUseCase(weatherForecast)
        val futureWeatherForecasts = getFutureWeatherForecastsUseCase(weatherForecast)
        _uiState.update { uiState ->
            uiState.setWeatherForecasts(
                todayWeatherForecast,
                futureWeatherForecastScreenModelsMapper.mapperToModels(
                    futureWeatherForecasts
                )
            )
        }
        weatherForecastsWasGet = true
    }

    private fun handleLocationError(error: Throwable?) = _uiState.update {
        it.setLocationError(getCommonSimpleDialogErrorParam(error))
    }

    private fun handleWeatherForecastNowError(error: Throwable?) = _uiState.update {
        val (status, simpleDialog) = getStatusAndErrorDialog(error, weatherForecastNowWasGet)
        it.setWeatherNowError(status, simpleDialog)
    }

    private fun handleWeatherForecastsError(error: Throwable?) = _uiState.update {
        val (status, simpleDialog) = getStatusAndErrorDialog(error, weatherForecastsWasGet)
        it.setWeatherForecastsError(status, simpleDialog)
    }

    private fun getStatusAndErrorDialog(error: Throwable?, dataWasGet: Boolean) = if (dataWasGet) {
        WeatherForecastStatus.READY to getCommonSimpleDialogErrorParam(
            error,
            CommonSimpleDialog.FailedUpdateError
        )
    } else {
        WeatherForecastStatus.ERROR to getCommonSimpleDialogErrorParam(error)
    }
}