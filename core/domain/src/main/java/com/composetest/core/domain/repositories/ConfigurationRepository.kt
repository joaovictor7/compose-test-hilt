package com.composetest.core.domain.repositories

import com.composetest.core.domain.models.ConfigurationModel

interface ConfigurationRepository {
    suspend fun getCurrentConfiguration(): ConfigurationModel?
    suspend fun getLastConfiguration(): ConfigurationModel?
    suspend fun update(configurationEntity: ConfigurationModel)
}