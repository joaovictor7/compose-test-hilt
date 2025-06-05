package com.composetest.feature.configuration.data.mapper

import com.composetest.core.database.data.dao.partialupdate.SecurityConfigurationUpdate
import com.composetest.core.database.data.entity.configuration.ConfigurationEntity
import com.composetest.core.database.data.entity.configuration.SecurityConfigurationEntity
import javax.inject.Inject

internal class SecurityConfigurationMapper @Inject constructor() {

    fun mapperToModel(entity: ConfigurationEntity?) = entity?.let {
        com.composetest.feature.configuration.domain.model.SecurityConfigurationModel(
            id = it.id,
            biometricLogin = it.securityEntity.biometricLogin
        )
    }

    fun mapperToEntity(model: com.composetest.feature.configuration.domain.model.SecurityConfigurationModel) =
        SecurityConfigurationEntity(
            biometricLogin = model.biometricLogin
        )

    fun mapperToUpdate(model: com.composetest.feature.configuration.domain.model.SecurityConfigurationModel) =
        SecurityConfigurationUpdate(
            id = model.id,
            biometricLogin = model.biometricLogin
        )
}