package com.composetest.core.designsystem.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.extensions.opacity
import com.composetest.core.designsystem.theme.ComposeTestTheme

//@Composable
//fun ShimmerEffect(
//    modifier: Modifier,
//    shimmerColors: List<Color> = listOf(
//        Color.LightGray.copy(alpha = 0.6f),
//        Color.LightGray.copy(alpha = 0.2f),
//        Color.LightGray.copy(alpha = 0.6f)
//    ),
//    animationDuration: Int = 1000
//) {
//    val transition = rememberInfiniteTransition()
//    val translateAnim = transition.animateFloat(
//        initialValue = -1000f,
//        targetValue = 1000f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(
//                durationMillis = animationDuration,
//                easing = LinearEasing
//            ),
//            repeatMode = RepeatMode.Restart
//        )
//    )
//
//    val brush = Brush.linearGradient(
//        colors = shimmerColors,
//        start = Offset(translateAnim.value, translateAnim.value), // Diagonal
//        end = Offset(translateAnim.value + 500f, translateAnim.value + 500f) // Diagonal
//    )
//
//    Box(
//        modifier = modifier
//            .background(brush = brush)
//    )
//}

@Composable
fun ShimmerEffect(
    modifier: Modifier,
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
) {
    val baseColor = MaterialTheme.colorScheme.onSurfaceVariant
    val baseColor1 = MaterialTheme.colorScheme.surface
    val shimmerColors = listOf(
        baseColor.copy(alpha = 0.3f),
        baseColor.copy(alpha = 0.5f),
        baseColor.copy(alpha = 1.0f),
        baseColor.copy(alpha = 0.5f),
        baseColor.copy(alpha = 0.3f),
    )
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = (1000 + widthOfShadowBrush).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
        end = Offset(x = translateAnimation.value, y = angleOfAxisY),
    )

    Box(modifier = modifier) {
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