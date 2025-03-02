package com.composetest.core.domain.usecases.configuration

import com.composetest.core.domain.repositories.ConfigurationRepository
import javax.inject.Inject

class GetThemeConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository,
) {
    operator fun invoke() = configurationRepository.getThemeConfiguration()
}