package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.configuration.ThemeConfigurationModel
import kotlinx.coroutines.flow.Flow

interface ConfigurationRepository {
    suspend fun insertDefaultUserConfiguration(userId: String)
    suspend fun getLastBiometricConfiguration(): Boolean?
    fun getThemeConfiguration(): Flow<ThemeConfigurationModel>
}