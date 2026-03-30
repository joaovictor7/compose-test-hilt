package com.composetest.feature.configuration.presenter.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation3.runtime.NavKey
import com.composetest.feature.configuration.navigation.destinaition.SecurityConfigurationNavKey
import com.composetest.feature.configuration.navigation.destinaition.ThemeConfigurationNavKey
import com.composetest.core.designsystem.R as DesignSystemResources
import com.composetest.feature.configuration.R as ConfigurationResources

internal enum class Configuration(
    @param:StringRes val textId: Int,
    @param:DrawableRes val iconId: Int,
    val navKey: NavKey
) {
    THEME(
        ConfigurationResources.string.configuration_theme_text,
        DesignSystemResources.drawable.ic_routine_medium,
        ThemeConfigurationNavKey
    ),
    SECURITY(
        ConfigurationResources.string.configuration_security_text,
        DesignSystemResources.drawable.ic_security_medium,
        SecurityConfigurationNavKey
    )
}