package com.composetest.core.designsystem.extensions

import android.graphics.Color
import androidx.activity.SystemBarStyle
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.AppThemeModel

// Same that `EdgeToEdge.kt`
private val defaultLightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val defaultDarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

val AppThemeModel.systemBarStyles
    get() = when (customTheme ?: theme) {
        Theme.AUTO -> SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) to
                SystemBarStyle.auto(defaultLightScrim, defaultDarkScrim)

        Theme.DARK -> SystemBarStyle.dark(Color.TRANSPARENT) to
                SystemBarStyle.auto(defaultLightScrim, defaultDarkScrim) { true }

        Theme.LIGHT -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT) to
                SystemBarStyle.auto(defaultLightScrim, defaultLightScrim) { false }
    }