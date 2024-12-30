package com.composetest.core.designsystem.components.switches

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.enums.switches.SwitchType
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun LabelSwitch(
    modifier: Modifier = Modifier,
    @StringRes labelTextId: Int,
    checked: Boolean,
    switchType: SwitchType? = null,
    onCheckedChange: (Boolean) -> Unit
) {
    LabelSwitch(
        modifier = modifier,
        labelText = stringResource(labelTextId),
        checked = checked,
        switchType = switchType,
        onCheckedChange = onCheckedChange
    )
}

@Composable
fun LabelSwitch(
    modifier: Modifier = Modifier,
    labelText: String,
    checked: Boolean,
    switchType: SwitchType? = null,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.bodyMedium
        )
        ThumbSwitch(
            checked = checked,
            type = switchType,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        LabelSwitch(labelText = "Teste", checked = true) { }
    }
}