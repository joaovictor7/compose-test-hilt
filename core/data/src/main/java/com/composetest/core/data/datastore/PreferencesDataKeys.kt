package com.composetest.core.data.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

sealed interface PreferencesDataKeys {
    data object Configuration : PreferencesDataKeys {
        val theme = stringPreferencesKey("theme")
        val dynamicColor = booleanPreferencesKey("dynamic_color")
    }

    data object Database : PreferencesDataKeys {
        val sqliteSecretKey = stringPreferencesKey("sqlite_secret_key")
    }
}
