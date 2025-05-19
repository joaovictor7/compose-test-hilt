package com.composetest.presentation.ui.dialogs

import com.composetest.R
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.designsystem.R as DesignSystemRes
import com.composetest.core.ui.R as UiRes

internal interface MainSimpleDialogParam : SimpleDialogParam {
    data object NetworkError : MainSimpleDialogParam {
        override val iconId = DesignSystemRes.drawable.ic_error_big
        override val titleId = R.string.error_alert_dialog_network_title
        override val textId = R.string.error_alert_dialog_network_text
        override val dismissButtonTextId = UiRes.string.close
    }

    data object GenericError : MainSimpleDialogParam {
        override val iconId = DesignSystemRes.drawable.ic_error_big
        override val titleId = DesignSystemRes.string.error_alert_dialog_generic_title
        override val textId = DesignSystemRes.string.error_alert_dialog_generic_text
        override val dismissButtonTextId = UiRes.string.close
    }
}