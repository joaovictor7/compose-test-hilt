package com.composetest.feature.configuration.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.composetest.core.designsystem.R as DesignSystemResources
import com.composetest.feature.configuration.R as ConfigurationResources

enum class Configuration(
    @StringRes val textId: Int,
    @DrawableRes val iconId: Int
) {
    THEME(
        ConfigurationResources.string.configuration_theme_text,
        DesignSystemResources.drawable.ic_brightness
    )
}