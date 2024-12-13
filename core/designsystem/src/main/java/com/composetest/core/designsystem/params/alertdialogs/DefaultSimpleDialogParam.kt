package com.composetest.core.designsystem.params.alertdialogs

import com.composetest.core.designsystem.R

sealed interface DefaultSimpleDialogParam : SimpleDialogParam {
    data object GenericError : DefaultSimpleDialogParam {
        override val iconId = R.drawable.ic_error_big
        override val titleId = R.string.error_alert_dialog_generic_title
        override val textId = R.string.error_alert_dialog_generic_text
        override val dismissButtonTextId = R.string.global_word_close
    }
    data object NetworkError : DefaultSimpleDialogParam {
        override val iconId = R.drawable.ic_error_big
        override val titleId = R.string.error_alert_dialog_generic_title
        override val textId = R.string.error_alert_dialog_generic_text
        override val dismissButtonTextId = R.string.global_word_close
    }
}