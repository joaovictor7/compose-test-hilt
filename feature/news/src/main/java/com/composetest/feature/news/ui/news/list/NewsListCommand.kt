package com.composetest.feature.news.ui.news.list

import com.composetest.core.domain.models.ArticleModel
import com.composetest.core.ui.interfaces.Command

internal sealed interface NewsListCommand : Command<NewsListCommandReceiver> {
    data class NavigateToFullNews(private val articleModel: ArticleModel) : NewsListCommand {
        override fun execute(commandReceiver: NewsListCommandReceiver) {
            commandReceiver.navigateToFullNews(articleModel)
        }
    }

    data object Refresh : NewsListCommand {
        override fun execute(commandReceiver: NewsListCommandReceiver) {
            commandReceiver.refresh()
        }
    }
}