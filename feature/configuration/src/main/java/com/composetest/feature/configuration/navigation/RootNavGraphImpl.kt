package com.composetest.feature.configuration.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navkey.configuration.ConfigurationNavKey
import com.composetest.feature.configuration.presenter.ui.configuration.ConfigurationScreen
import com.composetest.feature.configuration.presenter.ui.configuration.viewmodel.ConfigurationViewModel
import javax.inject.Inject

internal class RootNavGraphImpl @Inject constructor() : NavGraph {
    override fun EntryProviderScope<NavKey>.registerEntries(navBackStack: NavBackStack<NavKey>) {
        entry<ConfigurationNavKey> { _ ->
            val viewModel = hiltViewModel<ConfigurationViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ConfigurationScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                navBackStack = navBackStack,
                onExecuteIntent = viewModel::executeIntent,
            )
        }
    }
}
