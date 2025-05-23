package com.composetest.feature.news.ui.news.list

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.enums.ScreensAnalytic
import com.composetest.core.analytic.events.CommonAnalyticEvent
import com.composetest.core.domain.models.news.ArticleModel
import com.composetest.core.domain.usecases.news.GetTopHeadlinesUseCase
import com.composetest.core.router.destinations.news.FullNewsDestination
import com.composetest.core.router.models.NavigationModel
import com.composetest.core.router.utils.getDialogErrorDestination
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.utils.AsyncTaskUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class NewsListViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    private val analyticSender: AnalyticSender,
    @AsyncTaskUtilsQualifier(ScreensAnalytic.FULL_NEWS) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<NewsListUiState>, UiEvent<NewsListUiEvent>, NewsListCommandReceiver {

    override val commandReceiver = this

    private val _uiState = MutableStateFlow(NewsListUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<NewsListUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        sendOpenScreenAnalytic()
        getArticles()
    }

    override fun sendOpenScreenAnalytic() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(ScreensAnalytic.NEWS_LIST))
        }
    }

    override fun navigateToFullNews(article: ArticleModel) {
        _uiEvent.emitEvent(
            NewsListUiEvent.NavigateTo(
                NavigationModel(
                    FullNewsDestination(
                        imageUrl = article.urlToImage,
                        title = article.title,
                        description = article.description,
                        content = article.content
                    )
                )
            )
        )
    }

    override fun refresh() {
        getArticles()
    }

    private fun getArticles() {
        _uiState.update { it.setIsLoading(true) }
        asyncTaskUtils.runAsyncTask(
            coroutineScope = viewModelScope,
            onCompletion = { _uiState.update { it.setIsLoading(false) } },
            onError = ::requestErrorHandler
        ) {
            val articles = getTopHeadlinesUseCase()
            _uiState.update { it.setArticles(articles) }
        }
    }

    private fun requestErrorHandler(error: Throwable) {
        _uiState.update { it.setShowRetryButton() }
        _uiEvent.emitEvent(NewsListUiEvent.NavigateTo(getDialogErrorDestination(error)))
    }
}