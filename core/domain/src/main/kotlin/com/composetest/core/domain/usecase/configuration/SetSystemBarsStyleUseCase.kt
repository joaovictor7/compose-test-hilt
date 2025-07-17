package com.composetest.core.domain.usecase.configuration

import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.repository.SystemBarsThemeRepository
import javax.inject.Inject

class SetSystemBarsStyleUseCase @Inject constructor(
    private val systemBarsThemeRepository: SystemBarsThemeRepository,
) {

    operator fun invoke(theme: Theme?) {
        systemBarsThemeRepository.setStatusBarsTheme(theme)
    }
}