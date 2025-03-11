package com.composetest.feature.configuration.domain.usecases

import com.composetest.feature.configuration.data.repositories.ConfigurationRepositoryImpl
import javax.inject.Inject

internal class GetThemeConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepositoryImpl,
) {
    operator fun invoke() = configurationRepository.getThemeConfiguration()
}