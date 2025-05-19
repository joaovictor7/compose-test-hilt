package com.composetest.core.designsystem.extension

import android.graphics.Color
import androidx.activity.SystemBarStyle
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.model.AppThemeModel

val AppThemeModel.systemBarStyles
    get() = when (statusBarsTheme) {
        Theme.AUTO -> SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) to
                SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)

        Theme.DARK -> SystemBarStyle.dark(Color.TRANSPARENT) to
                SystemBarStyle.dark(Color.TRANSPARENT)

        Theme.LIGHT -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT) to
                SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
    }
