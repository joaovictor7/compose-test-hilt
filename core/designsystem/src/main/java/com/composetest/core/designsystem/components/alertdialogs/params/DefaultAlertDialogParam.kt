package com.composetest.core.designsystem.components.alertdialogs.params

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.composetest.core.designsystem.R

data class DefaultAlertDialogParam(
    @DrawableRes val iconId: Int,
    @StringRes val title: Int,
    @StringRes val text: Int,
    val onDismiss: ButtonAlertDialogParam? = null,
    val onConfirm: ButtonAlertDialogParam? = null
) {
    companion object {
        fun getGenericAlertDialogParam(onDismiss: () -> Unit) = DefaultAlertDialogParam(
            iconId = R.drawable.ic_error_big,
            title = R.string.error_alert_dialog_generic_title,
            text = R.string.error_alert_dialog_generic_text,
            onDismiss = ButtonAlertDialogParam(
                textId = R.string.global_word_close,
                onClick = onDismiss
            )
        )

        fun getNetworkAlertDialogParam(onDismiss: () -> Unit) = DefaultAlertDialogParam(
            iconId = R.drawable.ic_wifi_off,
            title = R.string.error_alert_dialog_network_title,
            text = R.string.error_alert_dialog_network_text,
            onDismiss = ButtonAlertDialogParam(
                textId = R.string.global_word_close,
                onClick = onDismiss
            )
        )
    }
}