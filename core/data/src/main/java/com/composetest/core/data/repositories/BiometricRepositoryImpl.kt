package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.preferencesdatastore.PreferencesDataKeys
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class BiometricRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) : BiometricRepository {

    override val biometricIsSet: Flow<Boolean?>
        get() = preferenceDataSource.getData { preferences ->
            preferences[PreferencesDataKeys.useBiometrics]
        }

    override suspend fun setBiometric(use: Boolean) {
        preferenceDataSource.setData(PreferencesDataKeys.useBiometrics, use)
    }
}