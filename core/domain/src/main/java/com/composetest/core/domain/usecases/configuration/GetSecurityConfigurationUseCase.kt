package com.composetest.core.domain.usecases.configuration

import com.composetest.core.domain.repositories.ConfigurationRepository
import javax.inject.Inject

class GetSecurityConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository,
) {
    suspend operator fun invoke() = configurationRepository.getSecurityConfiguration()
}