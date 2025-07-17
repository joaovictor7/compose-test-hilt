package com.composetest.core.domain.repository

import com.composetest.core.domain.model.ThemeConfigurationModel
import kotlinx.coroutines.flow.Flow

interface ConfigurationRepository {
    suspend fun insertDefaultUserConfiguration(userId: String)
    suspend fun getLastBiometricConfiguration(): Boolean?
    fun getThemeConfiguration(): Flow<ThemeConfigurationModel>
}