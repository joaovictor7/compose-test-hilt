package com.composetest.feature.news.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.news.NewsListDestination
import com.composetest.core.router.destinations.news.FullNewsDestination
import com.composetest.core.ui.extensions.buildComposable
import com.composetest.feature.news.ui.news.fully.FullNewsCommandReceiver
import com.composetest.feature.news.ui.news.fully.FullNewsScreen
import com.composetest.feature.news.ui.news.fully.FullNewsUiEvent
import com.composetest.feature.news.ui.news.fully.FullNewsUiState
import com.composetest.feature.news.ui.news.fully.FullNewsViewModel
import com.composetest.feature.news.ui.news.list.NewsListCommandReceiver
import com.composetest.feature.news.ui.news.list.NewsListScreen
import com.composetest.feature.news.ui.news.list.NewsListUiEvent
import com.composetest.feature.news.ui.news.list.NewsListUiState
import com.composetest.feature.news.ui.news.list.NewsListViewModel

fun NavGraphBuilder.newsNavGraph() {
    buildComposable<NewsListDestination, NewsListViewModel, NewsListUiState, NewsListUiEvent, NewsListCommandReceiver>(
        screen = NewsListScreen
    )
    buildComposable<FullNewsDestination, FullNewsViewModel, FullNewsUiState, FullNewsUiEvent, FullNewsCommandReceiver>(
        screen = FullNewsScreen
    )
}