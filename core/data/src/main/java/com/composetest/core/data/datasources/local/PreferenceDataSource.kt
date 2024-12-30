package com.composetest.core.data.datasources.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PreferenceDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun <T> setData(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    fun <T> getData(onGetValue: (Preferences) -> T) = dataStore.data.map {
        onGetValue.invoke(it)
    }
}