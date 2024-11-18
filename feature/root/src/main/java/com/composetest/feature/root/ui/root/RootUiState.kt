package com.composetest.feature.root.ui.root

import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.root.UserModalDrawerModel
import com.composetest.feature.root.enums.NavigationFeature
import com.composetest.feature.root.enums.NavigationLocal

internal data class RootUiState(
    val navigationFeatures: List<NavigationFeature> = emptyList(),
    val finishApp: Boolean = false,
    val userModalDrawerModel: UserModalDrawerModel = UserModalDrawerModel()
) : BaseUiState {
    val modalDrawerFeatures get() = navigationFeatures.filter { it.navigationLocal == NavigationLocal.MODAL_DRAWER }
    val bottomFeatures get() = navigationFeatures.filter { it.navigationLocal == NavigationLocal.BOTTOM }

    fun initUiState(
        navigationFeatures: List<NavigationFeature>,
        userModalDrawerModel: UserModalDrawerModel
    ) = copy(
        navigationFeatures = navigationFeatures,
        userModalDrawerModel = userModalDrawerModel
    )

    fun setNavigationFeatures(features: List<NavigationFeature>) = copy(navigationFeatures = features)
}
