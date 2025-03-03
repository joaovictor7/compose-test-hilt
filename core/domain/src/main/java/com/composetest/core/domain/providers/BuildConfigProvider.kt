package com.composetest.core.domain.providers

import com.composetest.core.domain.models.buildconfig.BuildConfigFieldsModel
import com.composetest.core.domain.models.buildconfig.BuildConfigModel

interface BuildConfigProvider {
    val buildConfig: BuildConfigModel
    val buildConfigFields: BuildConfigFieldsModel
}