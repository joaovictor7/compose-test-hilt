package com.composetest.feature.news.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destination.news.NewsListDestination
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.ui.util.transformDeepLinks
import com.composetest.feature.news.navigation.destination.FullNewsDestination
import com.composetest.feature.news.presenter.ui.news.full.FullNewsScreen
import com.composetest.feature.news.presenter.ui.news.full.FullNewsViewModel
import com.composetest.feature.news.presenter.ui.news.list.NewsListScreen
import com.composetest.feature.news.presenter.ui.news.list.NewsListViewModel

private const val NEWS_URI = "composetest://news"

object NewsNavGraph : NavGraph {
    override fun NavGraphBuilder.register(
        navController: NavHostController
    ) {
        composable<NewsListDestination>(
            deepLinks = transformDeepLinks(NEWS_URI)
        ) {
            val viewModel = hiltViewModel<NewsListViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            NewsListScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                onExecuteIntent = viewModel::executeIntent,
                navController = navController
            )
        }
        composable<FullNewsDestination> {
            val viewModel = hiltViewModel<FullNewsViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            FullNewsScreen(uiState = uiState)
        }
    }
}