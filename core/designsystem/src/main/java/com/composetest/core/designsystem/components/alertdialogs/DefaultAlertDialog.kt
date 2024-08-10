package com.composetest.core.designsystem.components.alertdialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.components.alertdialogs.params.ButtonAlertDialogParam
import com.composetest.core.designsystem.components.alertdialogs.params.DefaultAlertDialogParam
import com.composetest.core.designsystem.components.buttons.Button
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun DefaultAlertDialog(param: DefaultAlertDialogParam) {
    AlertDialog(
        onDismissRequest = param.onDismiss?.onClick ?: {},
        icon = { Icon(painter = painterResource(param.iconId), contentDescription = null) },
        iconContentColor = MaterialTheme.colorScheme.error,
        title = { Text(text = stringResource(param.title)) },
        text = { Text(text = stringResource(param.text)) },
        dismissButton = param.onDismiss?.let {
            button(it)
        },
        confirmButton = {
            param.onConfirm?.let { button(it) }
        }
    )
}

private fun button(buttonParam: ButtonAlertDialogParam) = @Composable {
    Button(
        text = stringResource(buttonParam.textId),
        onClick = buttonParam.onClick
    )
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        DefaultAlertDialog(param = DefaultAlertDialogParam.getNetworkAlertDialogParam { })
    }
}