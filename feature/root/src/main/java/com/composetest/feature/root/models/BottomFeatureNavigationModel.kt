package com.composetest.feature.root.models

import com.composetest.feature.root.enums.NavigationFeature

internal data class BottomFeatureNavigationModel(
    val feature: NavigationFeature,
    val selected: Boolean = false
)
