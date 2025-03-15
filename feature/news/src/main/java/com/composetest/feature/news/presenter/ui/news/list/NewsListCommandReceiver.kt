package com.composetest.feature.news.presenter.ui.news.list

import com.composetest.feature.news.domain.models.ArticleModel
import com.composetest.core.ui.interfaces.CommandReceiver

internal interface NewsListCommandReceiver : CommandReceiver<NewsListCommandReceiver> {
    fun navigateToFullNews(article: ArticleModel)
    fun refresh()
    fun dismissSimpleDialog()
}