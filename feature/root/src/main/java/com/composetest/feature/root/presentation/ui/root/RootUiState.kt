package com.composetest.feature.root.presentation.ui.root

import com.composetest.core.router.interfaces.Destination
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.root.presentation.enums.NavigationFeature
import com.composetest.feature.root.presentation.model.BottomFeatureNavigationModel
import com.composetest.feature.root.presentation.model.UserModalDrawerModel

internal data class RootUiState(
    val firstDestination: Destination? = null,
    val navGraphs: List<NavGraph> = emptyList(),
    val modalDrawerNavigationFeatures: List<NavigationFeature> = emptyList(),
    val bottomNavigationFeatures: List<BottomFeatureNavigationModel> = emptyList(),
    val userModalDrawerModel: UserModalDrawerModel = UserModalDrawerModel()
) {

    val showEditProfile get() = modalDrawerNavigationFeatures.any { it == NavigationFeature.PROFILE }
    val modalDrawerNavigationFeaturesToList get() = modalDrawerNavigationFeatures.filterNot { it.noText }

    fun initUiState(
        firstDestination: Destination?,
        navGraphs: Array<NavGraph>,
        modalDrawerNavigationFeatures: List<NavigationFeature>,
        bottomNavigationFeatures: List<BottomFeatureNavigationModel>,
        userModalDrawerModel: UserModalDrawerModel
    ) = copy(
        firstDestination = firstDestination,
        navGraphs = navGraphs.toList(),
        modalDrawerNavigationFeatures = modalDrawerNavigationFeatures,
        bottomNavigationFeatures = bottomNavigationFeatures,
        userModalDrawerModel = userModalDrawerModel
    )

    fun setUpdateUser(userModalDrawerModel: UserModalDrawerModel) =
        copy(userModalDrawerModel = userModalDrawerModel)

    fun setSelectedBottomNavigationFeature(feature: NavigationFeature) = copy(
        bottomNavigationFeatures = bottomNavigationFeatures.map {
            it.copy(selected = feature == it.feature)
        }
    )
}
