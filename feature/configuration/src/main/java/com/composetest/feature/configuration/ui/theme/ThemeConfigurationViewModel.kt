package com.composetest.feature.configuration.ui.theme

import com.composetest.common.extensions.orFalse
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.configuration.ThemeConfigurationModel
import com.composetest.core.domain.usecases.configuration.GetThemeConfigurationUseCase
import com.composetest.core.domain.usecases.configuration.UpdateThemeConfigurationUseCase
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.analytic.events.configuration.ThemeConfigurationEventAnalytic
import com.composetest.core.analytic.events.configuration.ThemeConfigurationScreenAnalytic
import com.composetest.feature.configuration.enums.ThemeConfiguration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ThemeConfigurationViewModel @Inject constructor(
    private val getThemeConfigurationUseCase: GetThemeConfigurationUseCase,
    private val updateThemeConfigurationUseCase: UpdateThemeConfigurationUseCase,
    override val analyticSender: AnalyticSender,
) : BaseViewModel(), UiState<ThemeConfigurationUiState>, ThemeConfigurationCommandReceiver {

    override val commandReceiver = this
    override val analyticScreen = ThemeConfigurationScreenAnalytic

    private var themeConfiguration: ThemeConfigurationModel? = null

    private val _uiState = MutableStateFlow(ThemeConfigurationUiState())
    override val uiState = _uiState.asStateFlow()

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
        analyticSender.sendEvent(
            ThemeConfigurationEventAnalytic.ChangeThemeConfiguration(
                theme?.name,
                dynamicColor
            )
        )
    }
}