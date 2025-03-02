package com.composetest.core.domain.usecases.configuration

import com.composetest.core.domain.models.configuration.SecurityConfigurationModel
import com.composetest.core.domain.repositories.ConfigurationRepository
import javax.inject.Inject

class UpdateSecurityConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository,
) {
    suspend operator fun invoke(
        securityConfigurationModel: SecurityConfigurationModel?
    ) = securityConfigurationModel?.let {
        configurationRepository.updateSecurityConfiguration(it)
    }
}