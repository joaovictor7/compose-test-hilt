package com.composetest.core.data.datasources.local

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

internal interface PreferenceDataSource {
    suspend fun <T> setData(key: Preferences.Key<T>, value: T)

    fun <T> getData(onGetValue: (Preferences) -> T): Flow<T>
}