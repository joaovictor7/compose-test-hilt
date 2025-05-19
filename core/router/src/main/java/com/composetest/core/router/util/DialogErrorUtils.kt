package com.composetest.core.router.util

import com.composetest.common.error.ApiError
import com.composetest.core.router.destination.dialog.GenericErrorDialog
import com.composetest.core.router.destination.dialog.NetworkErrorDialog
import com.composetest.core.router.model.NavigationModel

fun getDialogErrorDestination(error: Throwable): NavigationModel {
    val destination = when (error) {
        is ApiError.Network -> NetworkErrorDialog
        else -> GenericErrorDialog
    }
    return NavigationModel(destination)
}