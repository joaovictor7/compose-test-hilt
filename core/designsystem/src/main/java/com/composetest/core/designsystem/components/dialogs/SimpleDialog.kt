package com.composetest.core.designsystem.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.components.buttons.Button
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
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
        SimpleDialog(param = CommonSimpleDialog.NetworkError) {}
    }
}