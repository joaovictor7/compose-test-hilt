package com.composetest.core.domain.model.configuration

import com.composetest.core.domain.enums.Theme

data class ThemeConfigurationModel(
    var theme: Theme,
    var dynamicColor: Boolean,
)
