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
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.router.navkey.dialog.SimpleDialogNavKey

@Composable
fun SimpleDialog(
    navKey: SimpleDialogNavKey,
    onConfirm: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = { onDismiss?.invoke() },
        icon = { Icon(painter = painterResource(navKey.iconId), contentDescription = null) },
        iconContentColor = MaterialTheme.colorScheme.error,
        title = { Text(text = stringResource(navKey.titleId)) },
        text = { Text(text = stringResource(navKey.textId)) },
        dismissButton = navKey.dismissButtonTextId?.let {
            {
                Button(
                    text = stringResource(it),
                    onClick = { onDismiss?.invoke() }
                )
            }
        },
        confirmButton = {
            navKey.confirmButtonTextId?.let {
                Button(
                    text = stringResource(it),
                    onClick = { onConfirm?.invoke() }
                )
            }
        }
    )
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        SimpleDialog(
            navKey = SimpleDialogNavKey(
                iconId = R.string.error_alert_dialog_generic_text,
                titleId = R.string.error_alert_dialog_generic_title,
                textId = R.string.error_alert_dialog_generic_title,
            )
        )
    }
}