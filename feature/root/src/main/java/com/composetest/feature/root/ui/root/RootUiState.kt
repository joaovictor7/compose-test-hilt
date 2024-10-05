package com.composetest.feature.root.ui.root

import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.root.enums.NavigationBottomBar

internal data class RootUiState(
    val selectedBottomBarItem: NavigationBottomBar = RootViewModel.Companion.firstSelectedBottomBarItem,
    val finishApp: Boolean = false
) : BaseUiState {
    fun setSelectedBottomBarItem(selected: NavigationBottomBar) =
        copy(selectedBottomBarItem = selected)
}
