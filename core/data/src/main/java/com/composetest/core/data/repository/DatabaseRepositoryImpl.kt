package com.composetest.core.data.repository

import com.composetest.core.data.datasource.PreferenceDataSource
import com.composetest.core.data.datastore.PreferencesDataKeys
import com.composetest.core.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

internal class DatabaseRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) : DatabaseRepository {

    override suspend fun getSqliteSecretKey() = preferenceDataSource.getData { preferences ->
        preferences[PreferencesDataKeys.Database.sqliteSecretKey]
    }.firstOrNull()

    override suspend fun setSqliteSecretKey(secretKey: String) =
        preferenceDataSource.setData(PreferencesDataKeys.Database.sqliteSecretKey, secretKey)
}