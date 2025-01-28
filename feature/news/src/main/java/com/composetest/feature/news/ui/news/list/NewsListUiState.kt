package com.composetest.feature.news.ui.news.list

import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.domain.models.ArticleModel
import com.composetest.core.ui.interfaces.BaseUiState

internal data class NewsListUiState(
    val showScreen: Boolean = false,
    val articles: List<ArticleModel> = emptyList(),
    val simpleDialogParam: SimpleDialogParam? = null,
    val showWithoutNewsMsg: Boolean = false,
    override val isLoading: Boolean = false
) : BaseUiState {
    fun setIsLoading(isLoading: Boolean) = copy(
        isLoading = isLoading,
        showScreen = if (isLoading) true else showScreen,
        showWithoutNewsMsg = if (isLoading) false else showWithoutNewsMsg
    )

    fun setArticles(articles: List<ArticleModel>) = copy(articles = articles)
    fun setSimpleDialogParam(param: SimpleDialogParam? = null) = copy(
        simpleDialogParam = param,
        showWithoutNewsMsg = if (param != null) true else showWithoutNewsMsg
    )
}
