package com.composetest.feature.news.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.composetest.core.router.destinations.news.FullNewsDestination
import com.composetest.core.router.destinations.news.NewsListDestination
import com.composetest.feature.news.ui.news.full.FullNewsScreen
import com.composetest.feature.news.ui.news.full.FullNewsViewModel
import com.composetest.feature.news.ui.news.list.NewsListScreen
import com.composetest.feature.news.ui.news.list.NewsListViewModel

fun NavGraphBuilder.newsNavGraphs(navController: NavHostController) {
    composable<NewsListDestination> {
        val viewModel = hiltViewModel<NewsListViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        NewsListScreen(
            uiState = uiState,
            uiEvent = viewModel.uiEvent,
            onExecuteCommand = viewModel::executeCommand,
            navController = navController
        )
    }
    composable<FullNewsDestination> {
        val viewModel = hiltViewModel<FullNewsViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        FullNewsScreen(uiState = uiState)
    }
}