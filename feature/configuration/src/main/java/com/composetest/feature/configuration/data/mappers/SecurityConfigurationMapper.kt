package com.composetest.feature.configuration.data.mappers

import com.composetest.core.database.entities.configuration.ConfigurationEntity
import com.composetest.core.database.entities.configuration.SecurityConfigurationEntity
import com.composetest.core.database.partialupdate.SecurityConfigurationUpdate
import javax.inject.Inject

internal class SecurityConfigurationMapper @Inject constructor() {

    fun mapperToModel(entity: ConfigurationEntity?) = entity?.let {
        com.composetest.feature.configuration.domain.models.SecurityConfigurationModel(
            id = it.id,
            biometricLogin = it.securityEntity.biometricLogin
        )
    }

    fun mapperToEntity(model: com.composetest.feature.configuration.domain.models.SecurityConfigurationModel) = SecurityConfigurationEntity(
        biometricLogin = model.biometricLogin
    )

    fun mapperToUpdate(model: com.composetest.feature.configuration.domain.models.SecurityConfigurationModel) = SecurityConfigurationUpdate(
        id = model.id,
        biometricLogin = model.biometricLogin
    )
}