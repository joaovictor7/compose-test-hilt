package com.composetest.feature.login.presenter.ui.dialog

import com.composetest.core.designsystem.param.alertdialog.SimpleDialogParam
import com.composetest.feature.login.R
import com.composetest.core.designsystem.R as DesignSystemRes
import com.composetest.core.ui.R as UiRes

internal sealed interface SimpleDialogParam : SimpleDialogParam {
    data object ExpiredSession : SimpleDialogParam {
        override val iconId = DesignSystemRes.drawable.ic_person_off
        override val titleId = R.string.alert_dialog_session_invalid_title
        override val textId = R.string.alert_dialog_session_invalid_text
        override val dismissButtonTextId = UiRes.string.close
    }
}