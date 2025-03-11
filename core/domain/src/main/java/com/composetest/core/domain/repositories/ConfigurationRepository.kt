package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.configuration.ThemeConfigurationModel
import kotlinx.coroutines.flow.Flow

interface ConfigurationRepository {
    fun upset(configurationModel: ConfigurationModel)
    suspend fun getLastBiometricConfiguration(): Boolean?
    fun getThemeConfiguration(): Flow<ThemeConfigurationModel>
}