package com.composetest.feature.root.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.composetest.core.designsystem.R as DesignSystemResources
import com.composetest.feature.root.R as RootResources

internal enum class NavigationBottomBar(
    @DrawableRes val iconId: Int,
    @StringRes val labelId: Int
) {
    HOME(
        DesignSystemResources.drawable.ic_house_filled,
        RootResources.string.navigation_bottom_bar_home_item
    ),
    PROFILE(
        DesignSystemResources.drawable.ic_person_circle,
        RootResources.string.navigation_bottom_bar_profile_item
    ),
    CONFIGURATION(
        DesignSystemResources.drawable.ic_config_filled,
        RootResources.string.navigation_bottom_bar_configuration_item
    )
}