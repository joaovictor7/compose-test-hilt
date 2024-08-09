package com.composetest.core.data.constants.preferencesdatastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

internal object PreferencesDataKeys {
    val appTheme = stringPreferencesKey("app_theme")
    val dynamicColor = booleanPreferencesKey("dynamic_color")
}
