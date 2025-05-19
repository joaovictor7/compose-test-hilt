package com.composetest.core.designsystem.component.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.component.button.Button
import com.composetest.core.designsystem.param.alertdialog.SimpleDialogParam
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun SimpleDialog(
    param: SimpleDialogParam,
    onConfirm: ((SimpleDialogParam) -> Unit)? = null,
    onDismiss: ((SimpleDialogParam) -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = { onDismiss?.invoke(param) },
        icon = { Icon(painter = painterResource(param.iconId), contentDescription = null) },
        iconContentColor = MaterialTheme.colorScheme.error,
        title = { Text(text = stringResource(param.titleId)) },
        text = { Text(text = stringResource(param.textId)) },
        dismissButton = param.dismissButtonTextId?.let {
            {
                Button(
                    text = stringResource(it),
                    onClick = { onDismiss?.invoke(param) }
                )
            }
        },
        confirmButton = {
            param.confirmButtonTextId?.let {
                Button(
                    text = stringResource(it),
                    onClick = { onConfirm?.invoke(param) }
                )
            }
        }
    )
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        SimpleDialog(param = object : SimpleDialogParam {
            override val iconId = R.string.error_alert_dialog_generic_text
            override val titleId = R.string.error_alert_dialog_generic_title
            override val textId = R.string.error_alert_dialog_generic_title
        }) {}
    }
}