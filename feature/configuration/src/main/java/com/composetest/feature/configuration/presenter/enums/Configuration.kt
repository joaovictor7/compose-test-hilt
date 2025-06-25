package com.composetest.feature.configuration.presenter.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.composetest.core.router.interfaces.Destination
import com.composetest.feature.configuration.navigation.destinaition.SecurityConfigurationDestination
import com.composetest.feature.configuration.navigation.destinaition.ThemeConfigurationDestination
import com.composetest.core.designsystem.R as DesignSystemResources
import com.composetest.feature.configuration.R as ConfigurationResources

internal enum class Configuration(
    @param:StringRes val textId: Int,
    @param:DrawableRes val iconId: Int,
    val destination: Destination
) {
    THEME(
        ConfigurationResources.string.configuration_theme_text,
        DesignSystemResources.drawable.ic_routine_medium,
        ThemeConfigurationDestination
    ),
    SECURITY(
        ConfigurationResources.string.configuration_security_text,
        DesignSystemResources.drawable.ic_security_medium,
        SecurityConfigurationDestination
    )
}