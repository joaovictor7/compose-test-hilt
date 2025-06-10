package com.composetest.core.designsystem.extension

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun stringResource(@StringRes id: Int?) = id?.let { stringResource(it) }.orEmpty()