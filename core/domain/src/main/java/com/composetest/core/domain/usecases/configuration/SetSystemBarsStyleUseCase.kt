package com.composetest.core.domain.usecases.configuration

import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.repositories.SystemBarsThemeRepository
import javax.inject.Inject

class SetSystemBarsStyleUseCase @Inject constructor(
    private val systemBarsThemeRepository: SystemBarsThemeRepository,
) {

    operator fun invoke(theme: Theme?) {
        systemBarsThemeRepository.setStatusBarsTheme(theme)
    }
}