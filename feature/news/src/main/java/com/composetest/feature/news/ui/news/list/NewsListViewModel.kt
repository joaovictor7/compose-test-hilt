package com.composetest.feature.news.ui.news.list

import com.composetest.core.designsystem.utils.getCommonSimpleDialogErrorParam
import com.composetest.core.domain.models.ArticleModel
import com.composetest.core.domain.usecases.GetTopHeadlinesUseCase
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.news.FullNewsDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.news.analytics.home.NewsListScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class NewsListViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
) : BaseViewModel<NewsListUiState, NewsListUiEvent>(NewsListUiState()), NewsListCommandReceiver {

    override val commandReceiver = this
    override val analyticScreen = NewsListScreenAnalytic

    init {
        getArticles()
    }

    override fun navigateToFullNews(article: ArticleModel) {
        navigationManager.navigate(
            FullNewsDestination(
                imageUrl = article.urlToImage,
                title = article.title,
                description = article.description,
                content = article.content
            )
        )
    }

    override fun refresh() {
        getArticles()
    }

    override fun dismissSimpleDialog() {
        updateUiState { it.setSimpleDialogParam(null) }
    }

    private fun getArticles() {
        updateUiState { it.setIsLoading(true) }
        runAsyncTask(
            onCompletion = { updateUiState { it.setIsLoading(false) } },
            onError = ::requestErrorHandler
        ) {
            val articles = getTopHeadlinesUseCase()
            updateUiState { it.setArticles(articles) }
        }
    }

    private fun requestErrorHandler(error: Throwable) {
        updateUiState { uiState ->
            uiState.setSimpleDialogParam(getCommonSimpleDialogErrorParam(error))
        }
    }
}