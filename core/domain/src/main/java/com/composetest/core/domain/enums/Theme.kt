package com.composetest.core.domain.enums

import com.composetest.core.domain.enums.Theme.entries


enum class Theme {
    AUTO,
    DARK,
    LIGHT;

    companion object {
        fun getThemeByName(themeName: String?) = entries.find { it.name == themeName } ?: AUTO
    }
}