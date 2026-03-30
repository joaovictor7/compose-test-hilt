package com.composetest.feature.news.presenter.ui.news.list.viewmodel

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.api.event.CommonAnalyticEvent
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.designsystem.extension.dialogErrorNavigation
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
import com.composetest.feature.news.navigation.navkey.FullNewsNavKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
internal class NewsListViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    private val analyticSender: AnalyticSender,
    private val coroutineContext: CoroutineContext,
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
        viewModelScope.launch(coroutineContext) {
            asyncTaskUtils.runAsyncTask {
                analyticSender.sendEvent(CommonAnalyticEvent.OpenScreen(FullNewsScreenAnalytic))
            }
        }
    }

    override fun navigateToFullNews(article: ArticleModel) {
        _uiEvent.emitEvent(
            NewsListUiEvent.NavigateTo(
                NavigationModel(
                    FullNewsNavKey(
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
        viewModelScope.launch(coroutineContext) {
            asyncTaskUtils.runAsyncTask(
                onCompletion = { _uiState.update { it.setIsLoading(false) } },
                onError = ::requestErrorHandler
            ) {
                val articles = getTopHeadlinesUseCase()
                _uiState.update { it.setArticles(articles) }
            }
        }
    }

    private fun requestErrorHandler(error: Throwable) {
        _uiState.update { it.setShowRetryButton() }
        _uiEvent.emitEvent(NewsListUiEvent.NavigateTo(error.dialogErrorNavigation()))
    }
}