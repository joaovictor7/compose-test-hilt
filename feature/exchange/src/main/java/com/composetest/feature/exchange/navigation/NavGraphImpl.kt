package com.composetest.feature.exchange.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.navstack.LocalMainNavBackStack
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navkey.exchange.ExchangeListNavKey
import com.composetest.feature.exchange.navigation.navkey.ExchangeDetailNavKey
import com.composetest.feature.exchange.presenter.ui.detail.ExchangeDetailScreen
import com.composetest.feature.exchange.presenter.ui.detail.viewmodel.ExchangeDetailViewModel
import com.composetest.feature.exchange.presenter.ui.list.ExchangeListScreen
import com.composetest.feature.exchange.presenter.ui.list.viewmodel.ExchangeListViewModel
import javax.inject.Inject

internal class NavGraphImpl @Inject constructor() : NavGraph {
    override fun EntryProviderScope<NavKey>.registerEntries() {
        entry<ExchangeListNavKey> { _ ->
            val viewModel = hiltViewModel<ExchangeListViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExchangeListScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                onExecuteIntent = viewModel::executeIntent,
                navBackStack = LocalMainNavBackStack.current,
            )
        }
        entry<ExchangeDetailNavKey> { _ ->
            val viewModel = hiltViewModel<ExchangeDetailViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExchangeDetailScreen(uiState = uiState)
        }
    }
}
