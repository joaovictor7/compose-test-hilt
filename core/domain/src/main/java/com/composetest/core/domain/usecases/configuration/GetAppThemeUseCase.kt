package com.composetest.core.domain.usecases.configuration

import com.composetest.core.domain.mappers.AppThemeMapper
import com.composetest.core.domain.repositories.ConfigurationRepository
import com.composetest.core.domain.repositories.SystemBarsThemeRepository
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetAppThemeUseCase @Inject internal constructor(
    private val configurationRepository: ConfigurationRepository,
    private val statusBarsThemeRepository: SystemBarsThemeRepository,
    private val appThemeMapper: AppThemeMapper,
) {
    operator fun invoke() = configurationRepository.getThemeConfiguration()
        .combine(statusBarsThemeRepository.getStatusBarsTheme(), appThemeMapper::mapperToModel)
}