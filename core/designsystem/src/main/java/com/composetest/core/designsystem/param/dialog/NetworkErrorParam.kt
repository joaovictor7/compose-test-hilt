package com.composetest.core.designsystem.param.dialog

import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.param.alertdialog.SimpleDialogParam
import com.composetest.core.ui.R as UiRes

object NetworkErrorParam : SimpleDialogParam {
    override val iconId = R.drawable.ic_error_big
    override val titleId = R.string.error_alert_dialog_network_title
    override val textId = R.string.error_alert_dialog_network_text
    override val dismissButtonTextId = UiRes.string.close
}