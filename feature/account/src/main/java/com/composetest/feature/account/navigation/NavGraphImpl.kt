package com.composetest.feature.account.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navkey.profile.ProfileNavKey
import com.composetest.feature.account.presentation.ui.account.AccountScreen
import com.composetest.feature.account.presentation.ui.account.viewmodel.AccountViewModel
import javax.inject.Inject

internal class NavGraphImpl @Inject constructor() : NavGraph {
    override fun EntryProviderScope<NavKey>.registerEntries(navBackStack: NavBackStack<NavKey>) {
        entry<ProfileNavKey> { _ ->
            val viewModel = hiltViewModel<AccountViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            AccountScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                onExecuteIntent = viewModel::executeIntent,
                navBackStack = navBackStack,
            )
        }
    }
}
