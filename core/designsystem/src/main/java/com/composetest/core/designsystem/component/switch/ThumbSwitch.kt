package com.composetest.core.designsystem.component.switch

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.enums.switchs.SwitchType
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun ThumbSwitch(
    checked: Boolean,
    enable: Boolean = true,
    type: SwitchType? = null,
    onCheckedChange: (Boolean) -> Unit
) {
    Switch(
        checked = checked,
        enabled = enable,
        thumbContent = getThumbContent(type, checked),
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

private fun getThumbContent(type: SwitchType?, checked: Boolean): @Composable (() -> Unit)? =
    type?.let {
        {
            val iconId = if (checked) it.checkedThumb else it.uncheckedThumb
            Icon(
                modifier = Modifier.size(SwitchDefaults.IconSize),
                painter = painterResource(iconId),
                contentDescription = null
            )
        }
    }

@Composable
@Preview
private fun Preview() {
    var checked by remember { mutableStateOf(false) }
    ComposeTestTheme {
        ThumbSwitch(
            checked = checked,
            type = SwitchType.CHECK
        ) {
            checked = it
        }
    }
}