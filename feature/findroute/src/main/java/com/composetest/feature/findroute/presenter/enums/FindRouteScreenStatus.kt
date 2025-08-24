package com.composetest.feature.findroute.presenter.enums

import androidx.annotation.StringRes

internal enum class FindRouteScreenStatus(@param:StringRes val titleId: Int? = null) {
    INITIAL,
    READY,
    PERMISSION_NOT_GRANTED(null),
    TRY_AGAIN,
    NEEDS_LOCATION(null),
}