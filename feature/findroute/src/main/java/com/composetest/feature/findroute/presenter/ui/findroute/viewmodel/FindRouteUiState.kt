package com.composetest.feature.findroute.presenter.ui.findroute.viewmodel

import com.composetest.feature.findroute.presenter.enums.FindRouteScreenStatus

internal data class FindRouteUiState(
    val screenStatus: FindRouteScreenStatus = FindRouteScreenStatus.INITIAL,
) {
    val showFullScreenMsg
        get() = screenStatus in listOf(
            FindRouteScreenStatus.NEEDS_LOCATION,
            FindRouteScreenStatus.PERMISSION_NOT_GRANTED,
        ) || (screenStatus == FindRouteScreenStatus.TRY_AGAIN)

    val screenStatusIsPermissionNotGranted get() = screenStatus == FindRouteScreenStatus.PERMISSION_NOT_GRANTED

    val screenStatusIsTryAgain get() = screenStatus == FindRouteScreenStatus.TRY_AGAIN

    fun setScreenStatus(screenStatus: FindRouteScreenStatus) = copy(screenStatus = screenStatus)
}
