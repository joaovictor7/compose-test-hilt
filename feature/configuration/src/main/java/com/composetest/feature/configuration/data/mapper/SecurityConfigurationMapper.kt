package com.composetest.feature.configuration.data.mapper

import com.composetest.core.database.androidapi.dao.partialupdate.SecurityConfigurationUpdate
import com.composetest.core.database.androidapi.data.entity.configuration.ConfigurationEntity
import com.composetest.core.database.androidapi.data.entity.configuration.SecurityConfigurationEntity
import com.composetest.feature.configuration.domain.model.SecurityConfigurationModel
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