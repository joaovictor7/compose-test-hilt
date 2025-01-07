package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.ConfigurationDataSource
import com.composetest.core.data.mappers.ConfigurationMapper
import com.composetest.core.domain.models.ConfigurationModel
import com.composetest.core.domain.repositories.ConfigurationRepository
import javax.inject.Inject

internal class ConfigurationRepositoryImpl @Inject constructor(
    private val configurationDataSource: ConfigurationDataSource,
    private val configurationMapper: ConfigurationMapper,
) : ConfigurationRepository {

    override suspend fun getCurrentConfiguration(): ConfigurationModel? {
        val entity = configurationDataSource.getConfiguration()
        return configurationMapper(entity)
    }

    override suspend fun getLastConfiguration(): ConfigurationModel? {
        val entity = configurationDataSource.getLastConfiguration()
        return configurationMapper(entity)
    }

    override suspend fun update(configurationEntity: ConfigurationModel) {
        configurationDataSource.upsert(configurationMapper(configurationEntity))
    }
}