package com.composetest.feature.configuration.data.mappers

import com.composetest.core.database.entities.configuration.ConfigurationEntity
import com.composetest.feature.configuration.domain.models.ConfigurationModel
import javax.inject.Inject

internal class ConfigurationMapper @Inject constructor(
    private val securityConfigurationMapper: SecurityConfigurationMapper,
) {

    fun mapperToEntity(model: ConfigurationModel) = ConfigurationEntity(
        id = model.id,
        userId = model.userId,
        securityEntity = securityConfigurationMapper.mapperToEntity(model.security),
    )
}