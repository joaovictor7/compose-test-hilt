package com.composetest.feature.news.presenter.ui.news.list

import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.feature.news.domain.models.ArticleModel

internal data class NewsListUiState(
    val articles: List<ArticleModel> = emptyList(),
    val simpleDialogParam: SimpleDialogParam? = null,
    val showRetryButton: Boolean = false,
    val isLoading: Boolean = false
) {
    fun setIsLoading(isLoading: Boolean) = copy(
        isLoading = isLoading,
        showRetryButton = if (isLoading) false else showRetryButton
    )

    fun setArticles(articles: List<ArticleModel>) = copy(articles = articles)
    fun setSimpleDialogParam(param: SimpleDialogParam? = null) = copy(
        simpleDialogParam = param,
        showRetryButton = if (param != null) true else showRetryButton
    )
}
