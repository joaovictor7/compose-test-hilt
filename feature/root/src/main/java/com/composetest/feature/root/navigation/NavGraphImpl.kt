package com.composetest.feature.root.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.navstack.LocalMainNavBackStack
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navkey.root.RootNavKey
import com.composetest.feature.root.presentation.ui.root.RootScreen
import com.composetest.feature.root.presentation.ui.root.viewmodel.RootViewModel
import javax.inject.Inject

internal class NavGraphImpl @Inject constructor() : NavGraph {
    override fun EntryProviderScope<NavKey>.registerEntries() {
        entry<RootNavKey> { _ ->
            val viewModel = hiltViewModel<RootViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            RootScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                onExecuteIntent = viewModel::executeIntent,
                mainNavBackStack = LocalMainNavBackStack.current,
            )
        }
    }
}
