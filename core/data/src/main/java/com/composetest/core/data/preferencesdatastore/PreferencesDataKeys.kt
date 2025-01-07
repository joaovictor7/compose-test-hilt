package com.composetest.core.data.preferencesdatastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

internal object PreferencesDataKeys {
    val theme = stringPreferencesKey("theme")
    val dynamicColor = booleanPreferencesKey("dynamic_color")
    val sqliteSecretKey = stringPreferencesKey("sqlite_secret_key")
}
