package com.composetest.core.domain.providers

import com.composetest.core.domain.models.BuildConfigFieldsModel
import com.composetest.core.domain.models.BuildConfigModel

interface BuildConfigProvider {
    val buildConfig: BuildConfigModel
    val buildConfigFields: BuildConfigFieldsModel
}