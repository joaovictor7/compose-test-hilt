package com.composetest.core.designsystem.component.button

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.enums.button.LoadingButtonState
import com.composetest.core.designsystem.theme.ComposeTestTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

private const val DELAY_ANIMATION = 1000
private val animationDuration = 2.seconds

@Composable
fun LoadingButton(
    modifier: Modifier = Modifier,
    text: String,
    loadingState: LoadingButtonState = LoadingButtonState.IDLE,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val visualState = loadingButtonHandler(loadingState)
    Button(
        onClick = { if (visualState == LoadingButtonState.IDLE) onClick() },
        enabled = enabled,
        modifier = modifier,
    ) {
        AnimatedContent(
            targetState = visualState,
            transitionSpec = {
                fadeIn(animationSpec = tween(DELAY_ANIMATION))
                    .togetherWith(fadeOut(animationSpec = tween(DELAY_ANIMATION)))
            }
        ) { state ->
            when (state) {
                LoadingButtonState.IDLE -> {
                    Text(text = text, style = MaterialTheme.typography.labelLarge)
                }
                LoadingButtonState.LOADING -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.surfaceContainerHighest,
                        modifier = Modifier.size(24.dp)
                    )
                }
                LoadingButtonState.SUCCESS -> {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
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
            delay(animationDuration)
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
        var isLoading by remember { mutableStateOf(false) }
        LoadingButton(text = "Test") {
            isLoading = true
            Thread.sleep(2000L)
            isLoading = false
        }
    }
}