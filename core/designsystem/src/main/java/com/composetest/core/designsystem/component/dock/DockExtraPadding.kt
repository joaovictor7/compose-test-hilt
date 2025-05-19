package com.composetest.core.designsystem.component.dock

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.composetest.core.designsystem.composition.LocalRootDockProvider
import com.composetest.core.designsystem.extension.toDp

@Composable
fun DockExtraPadding() {
    Spacer(Modifier.height(LocalRootDockProvider.current.toDp))
}