package com.composetest.feature.login.presenter.ui.dialog

import com.composetest.core.router.destination.dialog.SimpleDialogDestination
import com.composetest.feature.login.R
import com.composetest.core.designsystem.R as DesignSystemRes
import com.composetest.core.ui.R as UiRes

internal object SimpleDialogDestination {
    val expiredSession = SimpleDialogDestination(
        iconId = DesignSystemRes.drawable.ic_person_off,
        titleId = R.string.alert_dialog_session_invalid_title,
        textId = R.string.alert_dialog_session_invalid_text,
        dismissButtonTextId = UiRes.string.close,
    )
}