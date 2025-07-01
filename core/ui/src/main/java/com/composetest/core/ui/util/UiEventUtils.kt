package com.composetest.core.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <T> UiEventsObserver(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(flow, lifecycle) {
        flow.flowWithLifecycle(lifecycle).collect(onEvent)
    }
}