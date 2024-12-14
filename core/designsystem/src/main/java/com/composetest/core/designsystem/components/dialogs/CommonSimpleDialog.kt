package com.composetest.core.designsystem.components.dialogs

import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam

sealed interface CommonSimpleDialog : SimpleDialogParam {
    data object GenericError : CommonSimpleDialog {
        override val iconId = R.drawable.ic_error_big
        override val titleId = R.string.error_alert_dialog_generic_title
        override val textId = R.string.error_alert_dialog_generic_text
        override val dismissButtonTextId = R.string.global_word_close
    }

    data object NetworkError : CommonSimpleDialog {
        override val iconId = R.drawable.ic_error_big
        override val titleId = R.string.error_alert_dialog_generic_title
        override val textId = R.string.error_alert_dialog_generic_text
        override val dismissButtonTextId = R.string.global_word_close
    }
}