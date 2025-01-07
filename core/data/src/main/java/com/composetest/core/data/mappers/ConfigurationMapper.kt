package com.composetest.core.data.mappers

import com.composetest.core.database.entities.ConfigurationEntity
import com.composetest.core.domain.models.ConfigurationModel
import javax.inject.Inject

internal class ConfigurationMapper @Inject constructor() {

    operator fun invoke(entity: ConfigurationEntity?) = entity?.let {
        ConfigurationModel(
            id = it.id,
            userId = it.userId,
            biometricLogin = it.biometricLogin,
        )
    }

    operator fun invoke(model: ConfigurationModel) = ConfigurationEntity(
        id = model.id,
        userId = model.userId,
        biometricLogin = model.biometricLogin
    )
}