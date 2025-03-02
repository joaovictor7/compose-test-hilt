package com.composetest.core.data.mappers

import com.composetest.core.database.entities.configuration.ConfigurationEntity
import com.composetest.core.database.entities.configuration.SecurityConfigurationEntity
import com.composetest.core.database.partialupdate.SecurityConfigurationUpdate
import com.composetest.core.domain.models.configuration.SecurityConfigurationModel
import javax.inject.Inject

internal class SecurityConfigurationMapper @Inject constructor() {

    fun mapperToModel(entity: ConfigurationEntity?) = entity?.let {
        SecurityConfigurationModel(
            id = it.id,
            biometricLogin = it.securityEntity.biometricLogin
        )
    }

    fun mapperToEntity(model: SecurityConfigurationModel) = SecurityConfigurationEntity(
        biometricLogin = model.biometricLogin
    )

    fun mapperToUpdate(model: SecurityConfigurationModel) = SecurityConfigurationUpdate(
        id = model.id,
        biometricLogin = model.biometricLogin
    )
}