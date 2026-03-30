package com.composetest.feature.news.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navkey.news.NewsListNavKey
import com.composetest.feature.news.navigation.navkey.FullNewsNavKey
import com.composetest.feature.news.presenter.ui.news.full.FullNewsScreen
import com.composetest.feature.news.presenter.ui.news.full.viewmodel.FullNewsViewModel
import com.composetest.feature.news.presenter.ui.news.list.NewsListScreen
import com.composetest.feature.news.presenter.ui.news.list.viewmodel.NewsListViewModel
import javax.inject.Inject

internal class NavGraphImpl @Inject constructor() : NavGraph {
    override fun EntryProviderScope<NavKey>.registerEntries(navBackStack: NavBackStack<NavKey>) {
        entry<NewsListNavKey> { _ ->
            val viewModel = hiltViewModel<NewsListViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            NewsListScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                onExecuteIntent = viewModel::executeIntent,
                navBackStack = navBackStack,
            )
        }
        entry<FullNewsNavKey> { _ ->
            val viewModel = hiltViewModel<FullNewsViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            FullNewsScreen(uiState = uiState)
        }
    }
}
