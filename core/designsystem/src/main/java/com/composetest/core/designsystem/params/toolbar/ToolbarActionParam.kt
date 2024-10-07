package com.composetest.core.designsystem.params.toolbar

import com.composetest.core.designsystem.enums.toolbar.ToolbarAction

data class ToolbarActionParam(
    val action: ToolbarAction,
    val onClickAction: () -> Unit
)