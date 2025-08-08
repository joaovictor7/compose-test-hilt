package com.composetest.core.designsystem.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalInspectionMode
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

val isPreview: Boolean @Composable get() = LocalInspectionMode.current

@Composable
fun rememberDebouncedClick(
    debounce: Duration = 1.seconds,
    onClick: () -> Unit
): () -> Unit {
    var enabled by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    return {
        if (enabled) {
            enabled = false
            onClick()
            coroutineScope.launch {
                delay(debounce)
                enabled = true
            }
        }
    }
}