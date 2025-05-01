package com.composetest.core.router.utils

import com.composetest.common.errors.ApiError
import com.composetest.core.router.destinations.dialogs.GenericErrorDialog
import com.composetest.core.router.destinations.dialogs.NetworkErrorDialog
import com.composetest.core.router.models.NavigationModel

fun getDialogErrorDestination(error: Throwable): NavigationModel {
    val destination = when (error) {
        is ApiError.Network -> NetworkErrorDialog
        else -> GenericErrorDialog
    }
    return NavigationModel(destination)
}