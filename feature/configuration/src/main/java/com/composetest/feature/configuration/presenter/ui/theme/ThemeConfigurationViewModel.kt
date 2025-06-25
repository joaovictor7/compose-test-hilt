package com.composetest.feature.configuration.presenter.ui.theme

import androidx.lifecycle.viewModelScope
import com.composetest.common.extension.orFalse
import com.composetest.core.analytic.event.CommonAnalyticEvent
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.model.ThemeConfigurationModel
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.configuration.analytic.event.ThemeConfigurationEventAnalytic
import com.composetest.feature.configuration.analytic.screen.ThemeConfigurationScreenAnalytic
import com.composetest.feature.configuration.domain.usecase.GetThemeConfigurationUseCase
import com.composetest.feature.configuration.domain.usecase.UpdateThemeConfigurationUseCase
import com.composetest.feature.configuration.presenter.enums.ThemeConfiguration
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
    private val analyticSender: AnalyticSender,
    @param:AsyncTaskUtilsQualifier(ThemeConfigurationScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<ThemeConfigurationUiState>, ThemeConfigurationIntentReceiver {

    override val intentReceiver = this

    private var themeConfiguration: ThemeConfigurationModel? = null

    private val _uiState = MutableStateFlow(ThemeConfigurationUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        sendOpenScreenAnalytic()
        initUiState()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ThemeConfigurationScreenAnalytic))
        }
    }

    override fun changeTheme(selectedTheme: ThemeConfiguration) {
        _uiState.update { it.setSelectedTheme(selectedTheme) }
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            sendChangeThemeAnalytic(theme = selectedTheme.theme)
            updateThemeConfigurationUseCase(themeConfiguration?.apply {
                theme = selectedTheme.theme
            })
        }
    }

    override fun changeDynamicColor(active: Boolean) {
        _uiState.update { it.setDynamicColors(active) }
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            sendChangeThemeAnalytic(dynamicColor = active)
            updateThemeConfigurationUseCase(themeConfiguration?.apply {
                dynamicColor = active
            })
        }
    }

    private fun initUiState() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
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