package com.composetest.core.designsystem.components.dock

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
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
            checkScrollStateDirection(scrollState, lastScrollOffset).also {
                lastScrollOffset = scrollState.value
            }
        }
    }
    LaunchedEffect(visible) {
        onVisibilityChange(visible)
    }
}

@Composable
fun DockVisibilityManager(
    lazyListState: LazyListState,
    onVisibilityChange: (Boolean) -> Unit
) {
    var previousIndex by rememberSaveable { mutableIntStateOf(0) }
    var previousScrollOffset by rememberSaveable { mutableIntStateOf(0) }
    val visible by remember {
        derivedStateOf {
            checkLazyListDirection(lazyListState, previousIndex, previousScrollOffset).also {
                previousIndex = lazyListState.firstVisibleItemIndex
                previousScrollOffset = lazyListState.firstVisibleItemScrollOffset
            }
        }
    }
    LaunchedEffect(visible) {
        onVisibilityChange(visible)
    }
}

private fun checkScrollStateDirection(scrollState: ScrollState, lastScrollOffset: Int) =
    scrollState.value <= lastScrollOffset

private fun checkLazyListDirection(
    lazyListState: LazyListState,
    previousIndex: Int,
    previousScrollOffset: Int
) = if (lazyListState.firstVisibleItemIndex == previousIndex) {
    lazyListState.firstVisibleItemScrollOffset <= previousScrollOffset
} else {
    lazyListState.firstVisibleItemIndex <= previousIndex
}