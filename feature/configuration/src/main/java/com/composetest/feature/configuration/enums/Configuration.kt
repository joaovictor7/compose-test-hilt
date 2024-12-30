package com.composetest.feature.configuration.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.composetest.core.router.destinations.configuration.ConfigurationSecurityDestination
import com.composetest.core.router.destinations.configuration.ConfigurationThemeDestination
import com.composetest.core.router.interfaces.Destination
import com.composetest.core.designsystem.R as DesignSystemResources
import com.composetest.feature.configuration.R as ConfigurationResources

internal enum class Configuration(
    @StringRes val textId: Int,
    @DrawableRes val iconId: Int,
    val destination: Destination
) {
    THEME(
        ConfigurationResources.string.configuration_theme_text,
        DesignSystemResources.drawable.ic_routine_medium,
        ConfigurationThemeDestination
    ),
    SECURITY(
        ConfigurationResources.string.configuration_security_text,
        DesignSystemResources.drawable.ic_security_medium,
        ConfigurationSecurityDestination
    )
}