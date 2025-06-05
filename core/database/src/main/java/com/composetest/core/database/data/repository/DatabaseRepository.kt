package com.composetest.core.database.data.repository

import com.composetest.core.data.datasource.PreferenceDataSource
import com.composetest.core.data.datastore.PreferencesDataKeys
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

internal class DatabaseRepository @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) {

    suspend fun getSqliteSecretKey() = preferenceDataSource.getData { preferences ->
        preferences[PreferencesDataKeys.Database.sqliteSecretKey]
    }.firstOrNull()

    suspend fun setSqliteSecretKey(secretKey: String) =
        preferenceDataSource.setData(PreferencesDataKeys.Database.sqliteSecretKey, secretKey)
}