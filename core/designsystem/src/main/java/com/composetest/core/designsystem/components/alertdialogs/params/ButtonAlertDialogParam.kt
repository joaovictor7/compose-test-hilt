package com.composetest.core.designsystem.components.alertdialogs.params

import androidx.annotation.StringRes

data class ButtonAlertDialogParam(
    @StringRes val textId: Int,
    val onClick: () -> Unit
)
