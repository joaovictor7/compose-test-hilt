package com.composetest.feature.configuration.domain.usecase

import com.composetest.feature.configuration.data.repository.ConfigurationRepositoryImpl
import javax.inject.Inject

internal class UpdateThemeConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepositoryImpl,
) {
    suspend operator fun invoke(
        themeConfigurationModel: com.composetest.core.domain.model.configuration.ThemeConfigurationModel?
    ) = themeConfigurationModel?.let {
        configurationRepository.updateThemeConfiguration(it)
    }
}