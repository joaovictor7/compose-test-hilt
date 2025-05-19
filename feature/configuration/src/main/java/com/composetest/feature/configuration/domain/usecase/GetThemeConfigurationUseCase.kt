package com.composetest.feature.configuration.domain.usecase

import com.composetest.feature.configuration.data.repository.ConfigurationRepositoryImpl
import javax.inject.Inject

internal class GetThemeConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepositoryImpl,
) {
    operator fun invoke() = configurationRepository.getThemeConfiguration()
}