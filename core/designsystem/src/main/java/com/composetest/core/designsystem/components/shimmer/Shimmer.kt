package com.composetest.core.designsystem.components.shimmer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.extensions.opacity
import com.composetest.core.designsystem.theme.ComposeTestTheme

@Composable
fun ShimmerEffect(modifier: Modifier) {
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = (1000 + 500).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )
    val baseShimmerColor = MaterialTheme.colorScheme.surfaceVariant
    val shimmerColors = listOf(
        baseShimmerColor,
        MaterialTheme.colorScheme.surface.opacity(0.2f),
        baseShimmerColor
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(x = translateAnimation.value - 500, y = 0.0f),
        end = Offset(x = translateAnimation.value, y = 270f),
    )

    Box(
        modifier = modifier.clip(MaterialTheme.shapes.small),
    ) {
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
        ShimmerEffect(Modifier.size(300.dp))
    }
}