package com.composetest.core.data.preferencesdatastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

internal object PreferencesDataKeys {
    val appTheme = stringPreferencesKey("app_theme")
    val sqliteSecretKey = stringPreferencesKey("sqlite_secret_key")
    val dynamicColor = booleanPreferencesKey("dynamic_color")
    val useBiometrics = booleanPreferencesKey("use_biometrics")
}
