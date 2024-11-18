package com.composetest.feature.root.models

import com.composetest.feature.root.enums.NavigationFeature

internal data class FeatureNavigationModel(
    val feature: NavigationFeature,
    val selected: Boolean
)
