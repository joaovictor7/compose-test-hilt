package com.composetest.feature.configuration.ui.theme

import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.managers.ConfigurationManager
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiState
import com.composetest.feature.configuration.analytics.theme.ConfigurationThemeEventAnalytic
import com.composetest.feature.configuration.analytics.theme.ConfigurationThemeScreenAnalytic
import com.composetest.feature.configuration.enums.ThemeConfiguration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationThemeViewModel @Inject constructor(
    private val configurationManager: ConfigurationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
) : BaseViewModel(), UiState<ConfigurationThemeUiState>, ConfigurationThemeCommandReceiver {

    private val _uiState = MutableStateFlow(ConfigurationThemeUiState())

    override val uiState = _uiState.asStateFlow()
    override val commandReceiver = this
    override val analyticScreen = ConfigurationThemeScreenAnalytic

    init {
        openScreenAnalytic()
        initUiState()
    }

    override fun changeTheme(selectedTheme: ThemeConfiguration) {
        _uiState.update { it.setSelectedTheme(selectedTheme) }
        runAsyncTask {
            sendChangeThemeAnalytic(theme = selectedTheme.theme)
            configurationManager.setTheme(selectedTheme.theme)
        }
    }

    override fun changeDynamicColor(active: Boolean) {
        _uiState.update { it.setDynamicColors(active) }
        runAsyncTask {
            sendChangeThemeAnalytic(dynamicColor = active)
            configurationManager.setDynamicColors(active)
        }
    }

    private fun initUiState() {
        runAsyncTask {
            configurationManager.fetchConfiguration()
            val currentTheme = configurationManager.getCurrentTheme()
            _uiState.update {
                it.initUiState(
                    ThemeConfiguration.entries,
                    ThemeConfiguration.getThemeConfiguration(currentTheme.theme),
                    currentTheme.dynamicColor
                )
            }
        }
    }

    private suspend fun sendChangeThemeAnalytic(
        dynamicColor: Boolean? = null,
        theme: Theme? = null
    ) {
        sendAnalyticsUseCase(ConfigurationThemeEventAnalytic.ChangeTheme(theme?.name, dynamicColor))
    }
}