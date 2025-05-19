package com.composetest.core.domain.usecase.configuration

import com.composetest.core.domain.mapper.AppThemeMapper
import com.composetest.core.domain.repository.ConfigurationRepository
import com.composetest.core.domain.repository.SystemBarsThemeRepository
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