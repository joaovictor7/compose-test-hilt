package com.composetest.feature.root.ui.root

import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.root.enums.NavigationFeature
import com.composetest.feature.root.enums.NavigationLocal

internal data class RootUiState(
    val navigationFeatures: List<NavigationFeature> = emptyList(),
    val selectedBottomNavigationFeature: NavigationFeature = RootViewModel.firstSelectedBottomBarItem,
    val finishApp: Boolean = false
) : BaseUiState {
    val modalDrawerFeatures get() = navigationFeatures.filter { it.navigationLocal == NavigationLocal.MODAL_DRAWER }
    val bottomFeatures get() = navigationFeatures.filter { it.navigationLocal == NavigationLocal.BOTTOM }

    fun initUiState(navigationFeatures: List<NavigationFeature>) =
        copy(navigationFeatures = navigationFeatures)
    fun setSelectedBottomNavigationFeature(selected: NavigationFeature) =
        copy(selectedBottomNavigationFeature = selected)
}
