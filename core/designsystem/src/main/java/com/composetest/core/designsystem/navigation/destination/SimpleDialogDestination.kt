package com.composetest.core.designsystem.navigation.destination

import com.composetest.core.designsystem.R
import com.composetest.core.router.destination.dialog.SimpleDialogDestination
import com.composetest.core.ui.R as UiRes

internal object SimpleDialogDestination {
    val genericError = SimpleDialogDestination(
        iconId = R.drawable.ic_error_big,
        titleId = R.string.error_alert_dialog_generic_title,
        textId = R.string.error_alert_dialog_generic_text,
        dismissButtonTextId = UiRes.string.close,
    )

    val networkError = SimpleDialogDestination(
        iconId = R.drawable.ic_error_big,
        titleId = R.string.error_alert_dialog_network_title,
        textId = R.string.error_alert_dialog_network_text,
        dismissButtonTextId = UiRes.string.close,
    )
}