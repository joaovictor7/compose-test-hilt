package com.composetest.domain.usecase

import com.composetest.core.domain.repository.ConfigurationRepository
import com.composetest.core.domain.repository.SystemBarsThemeRepository
import com.composetest.domain.mapper.AppThemeMapper
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

internal class GetAppThemeUseCase @Inject internal constructor(
    private val configurationRepository: ConfigurationRepository,
    private val statusBarsThemeRepository: SystemBarsThemeRepository,
    private val appThemeMapper: AppThemeMapper,
) {
    operator fun invoke() = configurationRepository.getThemeConfiguration()
        .combine(statusBarsThemeRepository.getStatusBarsTheme(), appThemeMapper::mapperToModel)
}