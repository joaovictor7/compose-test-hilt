package com.composetest.feature.root.presentation.model

import com.composetest.feature.root.presentation.enums.NavigationFeature

internal data class BottomFeatureNavigationModel(
    val feature: NavigationFeature,
    val selected: Boolean = false
)
