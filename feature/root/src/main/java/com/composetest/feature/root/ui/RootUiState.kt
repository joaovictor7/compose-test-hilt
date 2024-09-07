package com.composetest.feature.root.ui

import com.composetest.core.designsystem.components.dock.params.IconDockParam
import com.composetest.core.ui.interfaces.BaseUiState

internal data class RootUiState(
    val dockItems: List<IconDockParam> = emptyList(),
    val selectedDockItem: Int = 0,
    val finishApp: Boolean = false,
    val dockHeight: Int = 0
) : BaseUiState {
    fun setDockItems(dockItems: List<IconDockParam>) = copy(dockItems = dockItems)
    fun setSelectedDockItem(selectedIndex: Int) = copy(selectedDockItem = selectedIndex)
    fun setDockHeight(height: Int) = copy(dockHeight = height)
}
