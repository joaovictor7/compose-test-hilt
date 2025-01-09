package com.composetest.feature.news.ui.news.list

import com.composetest.core.domain.models.ArticleModel
import com.composetest.core.ui.interfaces.CommandReceiver

internal interface NewsListCommandReceiver : CommandReceiver<NewsListCommandReceiver> {
    fun navigateToFullNews(article: ArticleModel)
    fun refresh()
}