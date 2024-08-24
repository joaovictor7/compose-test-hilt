package com.composetest.core.designsystem.components.switches

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun ThumbCheckSwitch(
    checked: Boolean,
    enable: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
) {
    Switch(
        checked = checked,
        enabled = enable,
        thumbContent = getThumbContent(checked),
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

private fun getThumbContent(checked: Boolean) = @Composable {
    val iconId = if (checked) R.drawable.ic_check else R.drawable.ic_close
    Icon(
        modifier = Modifier.size(SwitchDefaults.IconSize),
        painter = painterResource(iconId),
        contentDescription = null
    )
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        ThumbCheckSwitch(
            checked = true
        ) { }
    }
}