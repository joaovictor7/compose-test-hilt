package com.composetest.feature.root.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.composetest.core.designsystem.R as DesignSystemResources
import com.composetest.feature.configuration.R as ConfigurationResources
import com.composetest.feature.home.R as HomeResources
import com.composetest.feature.profile.R as ProfileResources

internal enum class NavigationBottomBar(
    @DrawableRes val iconId: Int,
    @StringRes val labelId: Int
) {
    HOME(
        DesignSystemResources.drawable.ic_house_filled,
        HomeResources.string.home_title
    ),
    PROFILE(
        DesignSystemResources.drawable.ic_person_circle,
        ProfileResources.string.profile_title
    ),
    CONFIGURATION(
        DesignSystemResources.drawable.ic_config_filled,
        ConfigurationResources.string.configuration_title
    )
}