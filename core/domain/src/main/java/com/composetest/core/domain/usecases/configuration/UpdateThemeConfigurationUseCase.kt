package com.composetest.core.domain.usecases.configuration

import com.composetest.core.domain.models.configuration.ThemeConfigurationModel
import com.composetest.core.domain.repositories.ConfigurationRepository
import javax.inject.Inject

class UpdateThemeConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository,
) {
    suspend operator fun invoke(
        themeConfigurationModel: ThemeConfigurationModel?
    ) = themeConfigurationModel?.let {
        configurationRepository.updateThemeConfiguration(it)
    }
}