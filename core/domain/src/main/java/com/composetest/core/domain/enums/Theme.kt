package com.composetest.core.domain.enums

enum class Theme {
    AUTO,
    DARK,
    LIGHT;

    companion object {
        fun getThemeByName(themeName: String?) = entries.find { it.name == themeName } ?: AUTO
    }
}