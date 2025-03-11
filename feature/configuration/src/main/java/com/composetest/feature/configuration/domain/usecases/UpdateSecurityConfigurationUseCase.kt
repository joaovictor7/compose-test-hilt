package com.composetest.feature.configuration.domain.usecases

import com.composetest.feature.configuration.data.repositories.ConfigurationRepositoryImpl
import com.composetest.feature.configuration.domain.models.SecurityConfigurationModel
import javax.inject.Inject

internal class UpdateSecurityConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepositoryImpl,
) {
    suspend operator fun invoke(
        securityConfigurationModel: SecurityConfigurationModel?
    ) = securityConfigurationModel?.let {
        configurationRepository.updateSecurityConfiguration(it)
    }
}