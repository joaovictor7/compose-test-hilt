package com.composetest.feature.news.presenter.ui.news.list

import com.composetest.core.ui.interfaces.IntentReceiver
import com.composetest.feature.news.domain.model.ArticleModel

internal interface NewsListIntentReceiver : IntentReceiver<NewsListIntentReceiver> {
    fun navigateToFullNews(article: ArticleModel)
    fun refresh()
}