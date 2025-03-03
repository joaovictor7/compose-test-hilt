package com.composetest.feature.news.ui.news.full

import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.router.destinations.news.FullNewsDestination
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiState
import com.composetest.feature.news.analytics.newsdetail.FullNewsScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class FullNewsViewModel @Inject constructor(
    private val destination: FullNewsDestination,
    override val analyticSender: AnalyticSender
) : BaseViewModel(), UiState<FullNewsUiState> {


    override val analyticScreen = FullNewsScreenAnalytic

    private val _uiState = MutableStateFlow(FullNewsUiState())
    override val uiState = _uiState.asStateFlow()

    init {
        initUiState()
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