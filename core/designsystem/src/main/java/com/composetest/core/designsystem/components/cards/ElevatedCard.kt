package com.composetest.core.designsystem.components.cards

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.designsystem.theme.ComposeTestTheme
import androidx.compose.material3.ElevatedCard as MaterialElevatedCard

@Composable
fun ElevatedCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    MaterialElevatedCard(
        modifier = modifier,
        content = content,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    )
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        ElevatedCard {
            Text(
                modifier = Modifier.padding(spacings.eighteen),
                text = "Test"
            )
        }
    }
}