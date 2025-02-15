package com.composetest.feature.configuration.ui.configuration

import com.composetest.feature.configuration.enums.Configuration

internal data class ConfigurationUiState(
    val configurations: List<Configuration> = emptyList()
) {
    fun setConfigurations(configurations: List<Configuration>) =
        copy(configurations = configurations)
}
