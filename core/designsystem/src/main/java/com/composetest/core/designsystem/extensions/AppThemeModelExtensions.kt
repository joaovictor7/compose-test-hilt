package com.composetest.core.designsystem.extensions

import android.graphics.Color
import androidx.activity.SystemBarStyle
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.AppThemeModel

val AppThemeModel.systemBarStyles
    get() = when (customTheme ?: theme) {
        Theme.AUTO -> SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) to
                SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)

        Theme.DARK -> SystemBarStyle.dark(Color.TRANSPARENT) to
                SystemBarStyle.dark(Color.TRANSPARENT)

        Theme.LIGHT -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT) to
                SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
    }