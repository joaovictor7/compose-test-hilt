package com.composetest.feature.configuration.data.mappers

import com.composetest.core.database.entities.configuration.ConfigurationEntity
import com.composetest.core.domain.models.configuration.ConfigurationModel
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