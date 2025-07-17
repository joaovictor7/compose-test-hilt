package com.composetest.core.domain.provider

import com.composetest.core.domain.model.buildconfig.BuildConfigFieldsModel
import com.composetest.core.domain.model.buildconfig.BuildConfigModel

interface BuildConfigProvider {
    val buildConfig: BuildConfigModel
    val buildConfigFields: BuildConfigFieldsModel
}