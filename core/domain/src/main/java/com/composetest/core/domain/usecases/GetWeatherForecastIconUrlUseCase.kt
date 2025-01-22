package com.composetest.core.domain.usecases

import com.composetest.core.domain.repositories.ApiSettingsRepository
import javax.inject.Inject

class GetWeatherForecastIconUrlUseCase @Inject constructor(
    private val apiSettingsRepository: ApiSettingsRepository
) {
    operator fun invoke() = apiSettingsRepository.getWeatherForecastApiSettings().iconUrl
}