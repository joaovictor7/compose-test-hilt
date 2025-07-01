package com.composetest.core.designsystem.extension

import com.composetest.core.designsystem.navigation.destination.SimpleDialogDestination
import com.composetest.core.network.model.ApiError
import com.composetest.core.router.model.NavigationModel

fun Throwable.dialogErrorNavigation(): NavigationModel {
    val destination = when (this) {
        is ApiError.Network -> SimpleDialogDestination.networkError
        else -> SimpleDialogDestination.genericError
    }
    return NavigationModel(destination)
}