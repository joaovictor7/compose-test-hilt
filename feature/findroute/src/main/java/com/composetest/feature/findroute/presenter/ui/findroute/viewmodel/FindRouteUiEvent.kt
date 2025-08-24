package com.composetest.feature.findroute.presenter.ui.findroute.viewmodel

internal sealed interface FindRouteUiEvent {
    data object LaunchPermissionRequest : FindRouteUiEvent
}
