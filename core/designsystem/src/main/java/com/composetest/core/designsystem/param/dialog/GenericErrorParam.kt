package com.composetest.core.designsystem.param.dialog

import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.param.alertdialog.SimpleDialogParam
import com.composetest.core.ui.R as UiRes

object GenericErrorParam : SimpleDialogParam {
    override val iconId = R.drawable.ic_error_big
    override val titleId = R.string.error_alert_dialog_generic_title
    override val textId = R.string.error_alert_dialog_generic_text
    override val dismissButtonTextId = UiRes.string.close
}