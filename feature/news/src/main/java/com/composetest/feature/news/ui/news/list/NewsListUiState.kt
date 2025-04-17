package com.composetest.feature.news.ui.news.list

import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.domain.models.news.ArticleModel

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
