package com.composetest.feature.news.presenter.ui.news.list

import com.composetest.core.ui.interfaces.Command
import com.composetest.feature.news.domain.models.ArticleModel

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

    data object DismissSimpleDialog : NewsListCommand {
        override fun execute(commandReceiver: NewsListCommandReceiver) {
            commandReceiver.dismissSimpleDialog()
        }
    }
}