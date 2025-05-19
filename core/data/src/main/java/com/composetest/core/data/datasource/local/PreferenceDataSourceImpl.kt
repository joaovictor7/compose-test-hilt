package com.composetest.core.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.composetest.core.data.datasource.PreferenceDataSource
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PreferenceDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferenceDataSource {
    override suspend fun <T> setData(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override fun <T> getData(onGetValue: (Preferences) -> T) = dataStore.data.map {
        onGetValue.invoke(it)
    }
}