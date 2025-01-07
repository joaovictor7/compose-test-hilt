package com.composetest.core.data.managers

import com.composetest.core.data.mappers.ThemeMapper
import com.composetest.core.data.repositories.ThemeRepository
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.managers.ConfigurationManager
import com.composetest.core.domain.models.ConfigurationModel
import com.composetest.core.domain.repositories.ConfigurationRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class ConfigurationManagerImpl @Inject constructor(
    private val configurationRepository: ConfigurationRepository,
    private val themeRepository: ThemeRepository,
    private val themeMapper: ThemeMapper,
) : ConfigurationManager {

    override var currentConfiguration: ConfigurationModel? = null
        private set

    override val theme = themeRepository.theme
        .combine(themeRepository.statusBarsTheme) { theme, statusBarsTheme ->
            themeMapper(theme.theme, statusBarsTheme, theme.dynamicColor)
        }

    override suspend fun getCurrentTheme() = theme.first()

    override suspend fun fetchConfiguration() {
        currentConfiguration = configurationRepository.getCurrentConfiguration()
    }

    override suspend fun setTheme(theme: Theme) {
        themeRepository.setTheme(theme)
    }

    override fun setStatusBarsTheme(theme: Theme?) {
        themeRepository.setStatusBarsTheme(theme)
    }

    override suspend fun setDynamicColors(dynamicColor: Boolean) {
        themeRepository.setDynamicColor(dynamicColor)
    }

    override suspend fun setBiometricLogin(biometricLogin: Boolean) {
        updateConfiguration(currentConfiguration?.copy(biometricLogin = biometricLogin))
    }

    private suspend fun updateConfiguration(configuration: ConfigurationModel?) {
        currentConfiguration = configuration
        currentConfiguration?.let { configurationRepository.update(it) }
    }
}