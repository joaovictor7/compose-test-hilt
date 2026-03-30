package com.composetest.feature.login.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navkey.login.LoginNavKey
import com.composetest.feature.login.presentation.ui.login.LoginScreen
import com.composetest.feature.login.presentation.ui.login.viewmodel.LoginViewModel
import javax.inject.Inject

internal class NavGraphImpl @Inject constructor() : NavGraph {
    override fun EntryProviderScope<NavKey>.registerEntries(navBackStack: NavBackStack<NavKey>) {
        entry<LoginNavKey> {
            val viewModel = hiltViewModel<LoginViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            LoginScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                onExecuteIntent = viewModel::executeIntent,
                navBackStack = navBackStack,
            )
        }
    }
}
