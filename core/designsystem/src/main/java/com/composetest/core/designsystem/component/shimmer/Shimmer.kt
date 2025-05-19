package com.composetest.core.designsystem.component.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.extension.opacity
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.designsystem.util.getSharedShimmerOffset

@Composable
fun Shimmer(modifier: Modifier, offset: Float) {
    val baseShimmerColor = MaterialTheme.colorScheme.surfaceVariant
    val shimmerColors = listOf(
        baseShimmerColor,
        MaterialTheme.colorScheme.surface.opacity(0.2f),
        baseShimmerColor
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(x = offset - 500, y = 0.0f),
        end = Offset(x = offset, y = 270f),
    )

    Box(modifier = modifier.clip(MaterialTheme.shapes.medium)) {
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(brush)
        )
    }
}

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        val shimmerOffset by getSharedShimmerOffset()
        Shimmer(modifier = Modifier.size(300.dp), offset = shimmerOffset)
    }
}