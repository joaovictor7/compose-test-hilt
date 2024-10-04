package com.composetest.feature.root.ui

import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.root.enums.NavigationBottomBar
import com.composetest.feature.root.ui.RootViewModel.Companion.firstSelectedBottomBarItem

internal data class RootUiState(
    val selectedBottomBarItem: NavigationBottomBar = firstSelectedBottomBarItem,
    val finishApp: Boolean = false
) : BaseUiState {
    fun setSelectedBottomBarItem(selected: NavigationBottomBar) =
        copy(selectedBottomBarItem = selected)
}
