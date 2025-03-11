package com.composetest.feature.configuration.domain.usecases

import com.composetest.feature.configuration.data.repositories.ConfigurationRepositoryImpl
import javax.inject.Inject

internal class GetSecurityConfigurationUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepositoryImpl,
) {
    suspend operator fun invoke() = configurationRepository.getSecurityConfiguration()
}