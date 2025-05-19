package com.composetest.feature.configuration.domain.usecase

import com.composetest.feature.configuration.data.repository.ConfigurationRepositoryImpl
import com.composetest.feature.configuration.domain.model.SecurityConfigurationModel
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