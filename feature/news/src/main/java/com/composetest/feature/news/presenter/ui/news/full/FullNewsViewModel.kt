package com.composetest.feature.news.ui.news.full

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.enums.ScreensAnalytic
import com.composetest.core.analytic.events.CommonAnalyticEvent
import com.composetest.core.router.destinations.news.FullNewsDestination
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.utils.AsyncTaskUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class FullNewsViewModel @Inject constructor(
    private val destination: FullNewsDestination,
    private val analyticSender: AnalyticSender,
    @AsyncTaskUtilsQualifier(ScreensAnalytic.FULL_NEWS) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<FullNewsUiState> {

    private val _uiState = MutableStateFlow(FullNewsUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        sendOpenScreenAnalytic()
        initUiState()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ScreensAnalytic.FULL_NEWS))
        }
    }

    private fun initUiState() {
        _uiState.update {
            it.copy(
                imageUrl = destination.imageUrl,
                title = destination.title,
                description = destination.description,
                content = destination.content
            )
        }
    }
}