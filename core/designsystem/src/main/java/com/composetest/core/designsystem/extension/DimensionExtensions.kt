package com.composetest.core.designsystem.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

val Int.toDp: Dp @Composable get() = getDensity { this@toDp.toDp() }

val Float.toDp: Dp @Composable get() = getDensity { this@toDp.toDp() }

@Composable
private fun getDensity(onConvert: Density.() -> Dp) = with(LocalDensity.current) { onConvert() }