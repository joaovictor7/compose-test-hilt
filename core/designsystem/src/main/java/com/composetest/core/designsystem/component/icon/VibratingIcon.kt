package com.composetest.core.designsystem.component.icon

import androidx.annotation.DrawableRes
import androidx.annotation.IntRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.extension.toDp
import com.composetest.core.designsystem.theme.ComposeTestTheme

private const val DISPLACEMENT = 50f
private const val DURATION = 30

/**
 * @param amountVibrations Value must be between `-1` and `100`. Pass `-1` if you want infinity vibrations
 */
@Composable
fun VibratingIcon(
    modifier: Modifier = Modifier,
    @IntRange(from = -1, to = 100) amountVibrations: Int = -1,
    displacement: Float = DISPLACEMENT,
    duration: Int = DURATION,
    @DrawableRes iconId: Int,
    iconTint: Color? = null,
    contentDescription: String? = null,
    onAnimationFinished: (() -> Unit)? = null
) {
    val offsetX = remember { Animatable(0f) }
    LaunchedEffect(amountVibrations) {
        runVibrationAnimation(
            offsetX = offsetX,
            displacement = displacement,
            duration = duration,
            amountVibrations = amountVibrations,
            onAnimationEnd = onAnimationFinished
        )
    }
    Icon(
        painter = painterResource(iconId),
        contentDescription = contentDescription,
        tint = iconTint ?: LocalContentColor.current,
        modifier = modifier
            .offset { IntOffset(offsetX.value.toInt(), 0) }
            .padding(horizontal = displacement.toDp)
    )
}

suspend fun runVibrationAnimation(
    offsetX: Animatable<Float, AnimationVector1D>,
    displacement: Float,
    duration: Int,
    amountVibrations: Int,
    onAnimationEnd: (() -> Unit)?
) {
    val vibrationCount = if (amountVibrations == -1) Int.MAX_VALUE else amountVibrations
    repeat(vibrationCount) { count ->
        offsetX.animateTo(
            targetValue = displacement,
            animationSpec = tween(durationMillis = duration)
        )
        offsetX.animateTo(
            targetValue = -displacement,
            animationSpec = tween(durationMillis = duration)
        )
        if (count + 1 == vibrationCount) {
            offsetX.animateTo(0f)
            onAnimationEnd?.invoke()
            return
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        VibratingIcon(
            iconId = R.drawable.ic_fingerprint_extra_large,
            iconTint = MaterialTheme.colorScheme.error,
            amountVibrations = 3,
            duration = 90,
            displacement = 10f
        )
    }
}