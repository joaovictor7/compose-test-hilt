package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.preferencesdatastore.PreferencesDataKeys.sqliteSecretKey
import com.composetest.core.domain.repositories.DatabaseRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

internal class DatabaseRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) : DatabaseRepository {

    override suspend fun getSqliteSecretKey() = preferenceDataSource.getData { preferences ->
        preferences[sqliteSecretKey]
    }.firstOrNull()

    override suspend fun setSqliteSecretKey(secretKey: String) =
        preferenceDataSource.setData(sqliteSecretKey, secretKey)
}