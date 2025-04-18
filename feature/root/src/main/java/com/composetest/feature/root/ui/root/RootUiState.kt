package com.composetest.feature.root.ui.root

import com.composetest.core.router.interfaces.Destination
import com.composetest.feature.root.enums.NavigationFeature
import com.composetest.feature.root.models.BottomFeatureNavigationModel
import com.composetest.feature.root.models.UserModalDrawerModel

internal data class RootUiState(
    val firstDestination: Destination? = null,
    val modalDrawerNavigationFeatures: List<NavigationFeature> = emptyList(),
    val bottomNavigationFeatures: List<BottomFeatureNavigationModel> = emptyList(),
    val userModalDrawerModel: UserModalDrawerModel = UserModalDrawerModel()
) {

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

    fun setUpdateUser(userModalDrawerModel: UserModalDrawerModel) =
        copy(userModalDrawerModel = userModalDrawerModel)

    fun setSelectedBottomNavigationFeature(feature: NavigationFeature) = copy(
        bottomNavigationFeatures = bottomNavigationFeatures.map {
            it.copy(selected = feature == it.feature)
        }
    )
}
