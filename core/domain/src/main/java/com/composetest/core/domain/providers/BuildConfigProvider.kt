package com.composetest.core.domain.providers

import com.composetest.core.domain.models.BuildConfigModel

interface BuildConfigProvider {
    val get: BuildConfigModel
}