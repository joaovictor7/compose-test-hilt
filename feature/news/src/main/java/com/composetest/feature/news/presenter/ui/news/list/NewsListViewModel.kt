package com.composetest.feature.news.presenter.ui.news.list

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.event.CommonAnalyticEvent
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.router.extension.dialogErrorDestination
import com.composetest.core.router.model.NavigationModel
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.news.analytic.screen.FullNewsScreenAnalytic
import com.composetest.feature.news.analytic.screen.NewsListScreenAnalytic
import com.composetest.feature.news.domain.model.ArticleModel
import com.composetest.feature.news.domain.usecase.GetTopHeadlinesUseCase
import com.composetest.feature.news.navigation.destination.FullNewsDestination
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
    @param:AsyncTaskUtilsQualifier(NewsListScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
) : BaseViewModel(), UiState<NewsListUiState>, UiEvent<NewsListUiEvent>, NewsListIntentReceiver {

    override val intentReceiver = this

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
            analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(FullNewsScreenAnalytic))
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
        _uiEvent.emitEvent(NewsListUiEvent.NavigateTo(error.dialogErrorDestination()))
    }
}