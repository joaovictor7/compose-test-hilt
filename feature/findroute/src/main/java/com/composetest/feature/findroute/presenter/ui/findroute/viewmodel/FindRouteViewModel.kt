package com.composetest.feature.findroute.presenter.ui.findroute.viewmodel

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.api.event.CommonAnalyticEvent
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.router.destination.findroute.FindRouteDestination
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.enums.Permission
import com.composetest.core.ui.extension.uiStateValue
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.provider.LocationProvider
import com.composetest.core.ui.provider.PermissionProvider
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.findroute.analytic.screen.FindRouteScreenAnalytic
import com.composetest.feature.findroute.presenter.enums.FindRouteScreenStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class FindRouteViewModel @Inject constructor(
    private val analyticSender: AnalyticSender,
    private val locationProvider: LocationProvider,
    private val permissionProvider: PermissionProvider,
    private val findRouteDestination: FindRouteDestination,
    @param:AsyncTaskUtilsQualifier(FindRouteScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<FindRouteUiState>, UiEvent<FindRouteUiEvent>, FindRouteIntentReceiver {

    override val intentReceiver = this

    private val _uiState = MutableStateFlow(FindRouteUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<FindRouteUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        sendOpenScreenAnalytic()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(FindRouteScreenAnalytic))
        }
    }

    override fun onResume() {
        checkIsLocationEnabled()
    }

    private fun checkIsLocationEnabled() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            if (locationProvider.isLocationEnabled()) {
                checkPermissions()
            } else {
                _uiState.update { it.setScreenStatus(FindRouteScreenStatus.NEEDS_LOCATION) }
            }
        }
    }

    private fun checkPermissions() {
        val hasSomePermission = permissionProvider.somePermissionIsGranted(Permission.localization)
        val hasAllPermission = permissionProvider.permissionIsGranted(Permission.localization)
        var status = FindRouteScreenStatus.PERMISSION_NOT_GRANTED
        if (hasAllPermission || hasSomePermission) {
//            getLocationAndWeatherForecastsData()
            status = FindRouteScreenStatus.READY
        }
        if (!hasAllPermission && uiStateValue.screenStatus != FindRouteScreenStatus.PERMISSION_NOT_GRANTED) {
            _uiEvent.emitEvent(FindRouteUiEvent.LaunchPermissionRequest)
        }
        _uiState.update { it.setScreenStatus(status) }
    }
}