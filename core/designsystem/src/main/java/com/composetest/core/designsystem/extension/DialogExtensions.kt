package com.composetest.core.designsystem.extension

import com.composetest.core.designsystem.R
import com.composetest.core.network.model.ApiError
import com.composetest.core.router.destination.dialog.SimpleDialogDestination
import com.composetest.core.router.model.NavigationModel
import com.composetest.core.ui.R as UiRes

fun Throwable.dialogErrorNavigation(): NavigationModel {
    val destination = when (this) {
        is ApiError.Network -> SimpleDialogDestination(
            iconId = R.drawable.ic_error_big,
            titleId = R.string.error_alert_dialog_generic_title,
            textId = R.string.error_alert_dialog_generic_text,
            dismissButtonTextId = UiRes.string.close,
        )
        else -> SimpleDialogDestination(
            iconId = R.drawable.ic_error_big,
            titleId = R.string.error_alert_dialog_generic_title,
            textId = R.string.error_alert_dialog_generic_text,
            dismissButtonTextId = UiRes.string.close,
        )
    }
    return NavigationModel(destination)
}