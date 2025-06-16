package com.composetest.feature.news.analytic.screen

import com.composetest.feature.news.analytic.NewsModuleAnalytic

internal object FullNewsScreenAnalytic : NewsModuleAnalytic() {
    const val SCREEN = "full_news"
    override val screen = SCREEN
}