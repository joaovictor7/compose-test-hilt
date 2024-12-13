package com.composetest.enums

import com.composetest.R
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.designsystem.R as DesignSystemResources

internal sealed interface SimpleDialogParam : SimpleDialogParam {
    data object ExpiredSession : SimpleDialogParam {
        override val iconId = DesignSystemResources.drawable.ic_person_off
        override val titleId = R.string.alert_dialog_session_invalid_title
        override val textId = R.string.alert_dialog_session_invalid_text
        override val dismissButtonTextId = DesignSystemResources.string.global_word_close
    }
}