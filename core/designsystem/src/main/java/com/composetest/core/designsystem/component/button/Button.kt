package com.composetest.core.designsystem.component.button

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun Button(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Text(text = text, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        Button(text = "Label") { }
    }
}