package com.composetest.feature.weatherforecast.presenter.ui

import android.location.Location
import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.event.CommonAnalyticEvent
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.router.extension.dialogErrorDestination
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.enums.Permission
import com.composetest.core.ui.extension.uiStateValue
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.provider.LocationProvider
import com.composetest.core.ui.provider.PermissionProvider
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.weatherforecast.analytic.screen.WeatherForecastScreenAnalytic
import com.composetest.feature.weatherforecast.domain.usescase.GetFutureWeatherForecastUseCase
import com.composetest.feature.weatherforecast.domain.usescase.GetTodayWeatherForecastUseCase
import com.composetest.feature.weatherforecast.domain.usescase.GetWeatherForecastsUseCase
import com.composetest.feature.weatherforecast.domain.usescase.GetWeatherNowUseCase
import com.composetest.feature.weatherforecast.presenter.enums.WeatherForecastScreenStatus
import com.composetest.feature.weatherforecast.presenter.enums.WeatherForecastStatus
import com.composetest.feature.weatherforecast.presenter.mapper.FutureWeatherForecastScreenModelsMapper
import com.composetest.feature.weatherforecast.presenter.mapper.WeatherNowScreenModelMapper
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
    private val getTodayWeatherForecastUseCase: GetTodayWeatherForecastUseCase,
    private val getFutureWeatherForecastsUseCase: GetFutureWeatherForecastUseCase,
    private val weatherNowScreenModelMapper: WeatherNowScreenModelMapper,
    private val futureWeatherForecastScreenModelsMapper: FutureWeatherForecastScreenModelsMapper,
    private val locationProvider: LocationProvider,
    private val permissionProvider: PermissionProvider,
    private val analyticSender: AnalyticSender,
    @param:AsyncTaskUtilsQualifier(WeatherForecastScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<WeatherForecastUiState>, UiEvent<WeatherForecastUiEvent>,
    WeatherForecastIntentReceiver {

    override val intentReceiver = this

    private var location: Location? = null
    private var weatherForecastNowWasGet = false
    private var weatherForecastsWasGet = false

    private val _uiState = MutableStateFlow(WeatherForecastUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<WeatherForecastUiEvent>(replay = 1)
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        sendOpenScreenAnalytic()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(WeatherForecastScreenAnalytic))
        }
    }

    override fun onResume() {
        checkIsLocationEnabled()
    }

    override fun getLocationAndWeatherForecastsData() {
        _uiState.update { it.setScreenLoading() }
        asyncTaskUtils.runAsyncTask(
            coroutineScope = viewModelScope,
            onError = ::handleLocationError
        ) {
            location = locationProvider.getCurrentLocation()
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
        val weatherForecast = getWeatherForecastsUseCase(location.latitude, location.longitude)
        val todayWeatherForecast = getTodayWeatherForecastUseCase(weatherForecast)
        val futureWeatherForecasts = getFutureWeatherForecastsUseCase(weatherForecast)
        _uiState.update { uiState ->
            uiState.setWeatherForecasts(
                todayWeatherForecast,
                futureWeatherForecastScreenModelsMapper.mapperToModels(futureWeatherForecasts)
            )
        }
        weatherForecastsWasGet = true
    }

    private fun handleLocationError(error: Throwable) {
        _uiEvent.emitEvent(WeatherForecastUiEvent.NavigateTo(error.dialogErrorDestination()))
        _uiState.update { it.setTryAgainScreenError() }
    }

    private fun handleWeatherForecastNowError(error: Throwable) = _uiState.update {
        val status = getStatusErrorAndOpenDialog(error, weatherForecastNowWasGet)
        it.setWeatherNowStatus(status)
    }

    private fun handleWeatherForecastsError(error: Throwable) = _uiState.update {
        val status = getStatusErrorAndOpenDialog(error, weatherForecastsWasGet)
        it.setWeatherForecastsStatus(status)
    }

    private fun getStatusErrorAndOpenDialog(
        error: Throwable,
        dataWasGet: Boolean
    ): WeatherForecastStatus {
        _uiEvent.emitEvent(WeatherForecastUiEvent.NavigateTo(error.dialogErrorDestination()))
        return if (dataWasGet) {
            WeatherForecastStatus.READY
        } else {
            WeatherForecastStatus.ERROR
        }
    }

    private fun checkIsLocationEnabled() {
        if (uiStateValue.screenStatus in listOf(
                WeatherForecastScreenStatus.READY,
                WeatherForecastScreenStatus.TRY_AGAIN
            )
        ) return
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            if (locationProvider.isLocationEnabled()) {
                checkPermissions()
            } else {
                _uiState.update { it.setScreenStatus(WeatherForecastScreenStatus.NEEDS_LOCATION) }
            }
        }
    }

    private fun checkPermissions() {
        val hasSomePermission = permissionProvider.somePermissionIsGranted(Permission.localization)
        val hasAllPermission = permissionProvider.permissionIsGranted(Permission.localization)
        var status = WeatherForecastScreenStatus.PERMISSION_NOT_GRANTED
        if (hasAllPermission || hasSomePermission) {
            getLocationAndWeatherForecastsData()
            status = WeatherForecastScreenStatus.READY
        }
        if (!hasAllPermission && uiStateValue.screenStatus != WeatherForecastScreenStatus.PERMISSION_NOT_GRANTED) {
            _uiEvent.emitEvent(WeatherForecastUiEvent.LaunchPermissionRequest)
        }
        _uiState.update { it.setScreenStatus(status) }
    }
}