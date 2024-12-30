package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.preferencesdatastore.PreferencesDataKeys
import com.composetest.core.domain.managers.BiometricRepository
import javax.inject.Inject

internal class BiometricRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) : BiometricRepository {

    override val biometricIsSet
        get() = preferenceDataSource.getData { preferences ->
            preferences[PreferencesDataKeys.useBiometrics]
        }

    override suspend fun setUseBiometric(useBiometric: Boolean) {
        preferenceDataSource.setData(PreferencesDataKeys.useBiometrics, useBiometric)
    }
}