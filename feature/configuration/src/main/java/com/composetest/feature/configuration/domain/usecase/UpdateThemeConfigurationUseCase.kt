package com.composetest.feature.configuration.domain.usecase

import com.composetest.core.domain.model.ThemeConfigurationModel
import com.composetest.feature.configuration.data.repository.ConfigurationRepositoryImpl
import javax.inject.Inject

internal class UpdateThemeConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepositoryImpl,
) {
    suspend operator fun invoke(
        themeConfigurationModel: ThemeConfigurationModel?
    ) = themeConfigurationModel?.let {
        configurationRepository.updateThemeConfiguration(it)
    }
}