package com.composetest.feature.configuration.domain.usecase

import com.composetest.feature.configuration.data.repository.ConfigurationRepositoryImpl
import javax.inject.Inject

internal class GetSecurityConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepositoryImpl,
) {
    suspend operator fun invoke() = configurationRepository.getSecurityConfiguration()
}