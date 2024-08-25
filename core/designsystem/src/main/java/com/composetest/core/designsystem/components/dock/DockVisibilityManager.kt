package com.composetest.core.designsystem.components.dock

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun DockVisibilityManager(
    scrollState: ScrollState,
    onVisibilityChange: (Boolean) -> Unit
) {
    var lastScrollOffset by rememberSaveable { mutableIntStateOf(0) }
    val visible by remember {
        derivedStateOf {
            (scrollState.value <= lastScrollOffset).also {
                lastScrollOffset = scrollState.value
            }
        }
    }
    LaunchedEffect(visible) {
        onVisibilityChange(visible)
    }
}