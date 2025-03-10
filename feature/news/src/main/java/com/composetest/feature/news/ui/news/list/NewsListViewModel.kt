package com.composetest.feature.news.ui.news.list

import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.events.news.NewsListScreenAnalytic
import com.composetest.core.designsystem.utils.getCommonSimpleDialogErrorParam
import com.composetest.core.domain.models.news.ArticleModel
import com.composetest.core.domain.usecases.news.GetTopHeadlinesUseCase
import com.composetest.core.router.destinations.news.FullNewsDestination
import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
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
    override val analyticSender: AnalyticSender,
) : BaseViewModel(), UiState<NewsListUiState>, UiEvent<NewsListUiEvent>, NewsListCommandReceiver {

    override val commandReceiver = this
    override val analyticScreen = NewsListScreenAnalytic

    private val _uiState = MutableStateFlow(NewsListUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<NewsListUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        getArticles()
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

    override fun dismissSimpleDialog() {
        _uiState.update { it.setSimpleDialogParam(null) }
    }

    private fun getArticles() {
        _uiState.update { it.setIsLoading(true) }
        runAsyncTask(
            onCompletion = { _uiState.update { it.setIsLoading(false) } },
            onError = ::requestErrorHandler
        ) {
            val articles = getTopHeadlinesUseCase()
            _uiState.update { it.setArticles(articles) }
        }
    }

    private fun requestErrorHandler(error: Throwable) {
        _uiState.update { it.setSimpleDialogParam(getCommonSimpleDialogErrorParam(error)) }
    }
}