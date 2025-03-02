package com.composetest.feature.configuration.ui.theme

import com.composetest.common.extensions.orFalse
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.configuration.ThemeConfigurationModel
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.domain.usecases.configuration.GetThemeConfigurationUseCase
import com.composetest.core.domain.usecases.configuration.UpdateThemeConfigurationUseCase
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiState
import com.composetest.feature.configuration.analytics.theme.ConfigurationThemeEventAnalytic
import com.composetest.feature.configuration.analytics.theme.ConfigurationThemeScreenAnalytic
import com.composetest.feature.configuration.enums.ThemeConfiguration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationThemeViewModel @Inject constructor(
    private val getThemeConfigurationUseCase: GetThemeConfigurationUseCase,
    private val updateThemeConfigurationUseCase: UpdateThemeConfigurationUseCase,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
) : BaseViewModel(), UiState<ConfigurationThemeUiState>, ConfigurationThemeCommandReceiver {

    private var themeConfiguration: ThemeConfigurationModel? = null

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
            updateThemeConfigurationUseCase(themeConfiguration?.apply {
                theme = selectedTheme.theme
            })
        }
    }

    override fun changeDynamicColor(active: Boolean) {
        _uiState.update { it.setDynamicColors(active) }
        runAsyncTask {
            sendChangeThemeAnalytic(dynamicColor = active)
            updateThemeConfigurationUseCase(themeConfiguration?.apply {
                dynamicColor = active
            })
        }
    }

    private fun initUiState() {
        runAsyncTask {
            themeConfiguration = getThemeConfigurationUseCase().first()
            _uiState.update {
                it.initUiState(
                    ThemeConfiguration.entries,
                    ThemeConfiguration.getThemeConfiguration(themeConfiguration?.theme),
                    themeConfiguration?.dynamicColor.orFalse
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