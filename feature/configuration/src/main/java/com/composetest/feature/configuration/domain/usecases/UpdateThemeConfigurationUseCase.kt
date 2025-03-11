package com.composetest.feature.configuration.domain.usecases

import com.composetest.feature.configuration.data.repositories.ConfigurationRepositoryImpl
import javax.inject.Inject

internal class UpdateThemeConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepositoryImpl,
) {
    suspend operator fun invoke(
        themeConfigurationModel: com.composetest.core.domain.models.configuration.ThemeConfigurationModel?
    ) = themeConfigurationModel?.let {
        configurationRepository.updateThemeConfiguration(it)
    }
}