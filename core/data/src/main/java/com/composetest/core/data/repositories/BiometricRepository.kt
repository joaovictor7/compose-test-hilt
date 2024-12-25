package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.preferencesdatastore.PreferencesDataKeys
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class BiometricRepository @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) {

    val biometricIsSet: Flow<Boolean?>
        get() = preferenceDataSource.getData { preferences ->
            preferences[PreferencesDataKeys.useBiometrics]
        }

    suspend fun setBiometric(use: Boolean) {
        preferenceDataSource.setData(PreferencesDataKeys.useBiometrics, use)
    }
}