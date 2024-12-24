package com.composetest.core.data.repositories

import com.composetest.core.data.preferencesdatastore.PreferencesDataKeys.sqliteSecretKey
import com.composetest.core.data.datasources.local.PreferenceDataSource
import com.composetest.core.domain.repositories.SecretKeyRepository
import javax.inject.Inject

internal class SecretKeyRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) : SecretKeyRepository {

    override suspend fun getSqliteSecretKey() = preferenceDataSource.getData { preferences ->
        preferences[sqliteSecretKey]
    }

    override suspend fun setSqliteSecretKey(secretKey: String) =
        preferenceDataSource.setData(sqliteSecretKey, secretKey)
}