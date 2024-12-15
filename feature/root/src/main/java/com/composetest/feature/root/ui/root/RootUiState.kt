package com.composetest.feature.root.ui.root

import com.composetest.core.router.interfaces.Destination
import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.root.models.UserModalDrawerModel
import com.composetest.feature.root.enums.NavigationFeature
import com.composetest.feature.root.models.BottomFeatureNavigationModel

internal data class RootUiState(
    val firstDestination: Destination? = null,
    val modalDrawerNavigationFeatures: List<NavigationFeature> = emptyList(),
    val bottomNavigationFeatures: List<BottomFeatureNavigationModel> = emptyList(),
    val userModalDrawerModel: UserModalDrawerModel = UserModalDrawerModel()
) : BaseUiState {

    val currentScreenTitle get() = bottomNavigationFeatures.find { it.selected }?.feature?.textId
    val showEditProfile get() = modalDrawerNavigationFeatures.any { it == NavigationFeature.PROFILE }
    val modalDrawerNavigationFeaturesToList get() = modalDrawerNavigationFeatures.filterNot { it.noText }

    fun initUiState(
        firstDestination: Destination?,
        modalDrawerNavigationFeatures: List<NavigationFeature>,
        bottomNavigationFeatures: List<BottomFeatureNavigationModel>,
        userModalDrawerModel: UserModalDrawerModel
    ) = copy(
        firstDestination = firstDestination,
        modalDrawerNavigationFeatures = modalDrawerNavigationFeatures,
        bottomNavigationFeatures = bottomNavigationFeatures,
        userModalDrawerModel = userModalDrawerModel
    )

    fun setSelectedBottomNavigationFeature(feature: NavigationFeature) = copy(
        bottomNavigationFeatures = bottomNavigationFeatures.map {
            it.copy(selected = feature == it.feature)
        }
    )
}
