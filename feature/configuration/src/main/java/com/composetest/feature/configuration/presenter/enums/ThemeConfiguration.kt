package com.composetest.feature.configuration.presenter.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.composetest.core.domain.enums.Theme
import com.composetest.core.designsystem.R as DesignSystemResources
import com.composetest.feature.configuration.R as ConfigurationResources

internal enum class ThemeConfiguration(
    @param:StringRes val textId: Int,
    @param:DrawableRes val iconId: Int,
    val theme: Theme
) {
    DARK(
        textId = ConfigurationResources.string.configuration_theme_dark_text,
        iconId = DesignSystemResources.drawable.ic_bedtime,
        theme = Theme.DARK
    ),
    LIGHT(
        textId = ConfigurationResources.string.configuration_theme_light_text,
        iconId = DesignSystemResources.drawable.ic_sunny,
        theme = Theme.LIGHT
    ),
    AUTO(
        textId = ConfigurationResources.string.configuration_theme_auto_text,
        iconId = DesignSystemResources.drawable.ic_phone_android,
        theme = Theme.AUTO
    );

    companion object {
        fun getThemeConfiguration(theme: Theme?) = entries.find { it.theme == theme } ?: AUTO
    }
}