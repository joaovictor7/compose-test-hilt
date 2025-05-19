package com.composetest.feature.news.presenter.ui.news.list

import com.composetest.feature.news.domain.model.ArticleModel

internal data class NewsListUiState(
    val articles: List<ArticleModel> = emptyList(),
    val showRetryButton: Boolean = false,
    val isLoading: Boolean = false
) {
    fun setIsLoading(isLoading: Boolean) = copy(
        isLoading = isLoading,
        showRetryButton = showRetryButton.takeIf { !isLoading } == true
    )

    fun setArticles(articles: List<ArticleModel>) = copy(articles = articles)
    fun setShowRetryButton() = copy(showRetryButton = true)
}
