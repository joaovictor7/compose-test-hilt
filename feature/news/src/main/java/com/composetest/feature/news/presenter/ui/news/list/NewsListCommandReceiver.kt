package com.composetest.feature.news.presenter.ui.news.list

import com.composetest.core.ui.interfaces.CommandReceiver
import com.composetest.feature.news.domain.models.ArticleModel

internal interface NewsListCommandReceiver : CommandReceiver<NewsListCommandReceiver> {
    fun navigateToFullNews(article: ArticleModel)
    fun refresh()
}