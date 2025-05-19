package com.composetest.feature.news.presenter.ui.news.list

import com.composetest.core.ui.interfaces.Intent
import com.composetest.feature.news.domain.model.ArticleModel

internal sealed interface NewsListIntent : Intent<NewsListIntentReceiver> {
    data class NavigateToFullNews(private val articleModel: ArticleModel) : NewsListIntent {
        override fun execute(intentReceiver: NewsListIntentReceiver) {
            intentReceiver.navigateToFullNews(articleModel)
        }
    }

    data object Refresh : NewsListIntent {
        override fun execute(intentReceiver: NewsListIntentReceiver) {
            intentReceiver.refresh()
        }
    }
}