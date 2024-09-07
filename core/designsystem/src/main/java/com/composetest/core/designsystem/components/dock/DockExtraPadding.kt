package com.composetest.core.designsystem.components.dock

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.composetest.core.designsystem.compositions.LocalRootDockProvider
import com.composetest.core.designsystem.extensions.toDp

@Composable
fun DockExtraPadding() {
    Spacer(Modifier.height(LocalRootDockProvider.current.toDp))
}