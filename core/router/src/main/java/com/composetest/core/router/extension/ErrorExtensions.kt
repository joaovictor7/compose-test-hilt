package com.composetest.core.router.extension

import com.composetest.core.network.model.ApiError
import com.composetest.core.router.destination.dialog.GenericErrorDialog
import com.composetest.core.router.destination.dialog.NetworkErrorDialog
import com.composetest.core.router.model.NavigationModel

fun Throwable.dialogErrorDestination(): NavigationModel {
    val destination = when (this) {
        is ApiError.Network -> NetworkErrorDialog
        else -> GenericErrorDialog
    }
    return NavigationModel(destination)
}