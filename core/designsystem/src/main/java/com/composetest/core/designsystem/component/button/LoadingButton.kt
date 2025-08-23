package com.composetest.core.designsystem.component.button

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.component.button.enums.LoadingButtonState
import com.composetest.core.designsystem.extension.toDp
import com.composetest.core.designsystem.theme.ComposeTestTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val ANIMATION_DURATION = 600
private const val SUCCESS_ANIMATION_DURATION = 2000L
private val minComponentHeight = 24.dp

@Composable
fun LoadingButton(
    modifier: Modifier = Modifier,
    text: String,
    loadingState: LoadingButtonState = LoadingButtonState.IDLE,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val visualState = loadingButtonHandler(loadingState)
    var textSize by remember { mutableIntStateOf(0) }
    val spec: FiniteAnimationSpec<Float> = tween(ANIMATION_DURATION)
    Button(
        onClick = { if (visualState == LoadingButtonState.IDLE) onClick() },
        enabled = enabled,
        modifier = modifier,
    ) {
        AnimatedContent(
            targetState = visualState,
            transitionSpec = {
                (fadeIn(spec) + scaleIn(spec))
                    .togetherWith(fadeOut(spec) + scaleOut(spec))
            }
        ) { state ->
            Box(
                modifier = Modifier.defaultMinSize(
                    minWidth = textSize.toDp,
                    minHeight = minComponentHeight,
                ),
                contentAlignment = Alignment.Center,
            ) {
                when (state) {
                    LoadingButtonState.IDLE -> {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.onSizeChanged {
                                textSize = it.width
                            }
                        )
                    }
                    LoadingButtonState.LOADING -> {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.surfaceContainerHighest,
                            modifier = Modifier.size(minComponentHeight)
                        )
                    }
                    LoadingButtonState.SUCCESS -> {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun loadingButtonHandler(loadingState: LoadingButtonState): LoadingButtonState {
    var visualState by remember { mutableStateOf(LoadingButtonState.IDLE) }
    LaunchedEffect(loadingState) {
        if (loadingState == LoadingButtonState.SUCCESS && visualState == LoadingButtonState.LOADING) {
            visualState = LoadingButtonState.SUCCESS
            delay(SUCCESS_ANIMATION_DURATION)
            visualState = LoadingButtonState.IDLE
        } else {
            visualState = loadingState
        }
    }
    return visualState
}

@Composable
@PreviewLightDark
private fun Preview() {
    ComposeTestTheme {
        var isLoading by remember { mutableStateOf(LoadingButtonState.IDLE) }
        val scope = rememberCoroutineScope()
        LoadingButton(text = "Test", loadingState = isLoading) {
            isLoading = LoadingButtonState.LOADING
            scope.launch {
                delay(2000L)
                isLoading = LoadingButtonState.SUCCESS
            }
        }
    }
}