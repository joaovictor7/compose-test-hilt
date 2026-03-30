package com.composetest.feature.configuration.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.feature.configuration.navigation.destinaition.SecurityConfigurationNavKey
import com.composetest.feature.configuration.navigation.destinaition.ThemeConfigurationNavKey
import com.composetest.feature.configuration.presenter.ui.security.SecurityConfigurationScreen
import com.composetest.feature.configuration.presenter.ui.security.viewmodel.SecurityConfigurationViewModel
import com.composetest.feature.configuration.presenter.ui.theme.ThemeConfigurationScreen
import com.composetest.feature.configuration.presenter.ui.theme.viewmodel.ThemeConfigurationViewModel
import javax.inject.Inject

internal class NavGraphImpl @Inject constructor() : NavGraph {
    override fun EntryProviderScope<NavKey>.registerEntries(navBackStack: NavBackStack<NavKey>) {
        entry<ThemeConfigurationNavKey> { _ ->
            val viewModel = hiltViewModel<ThemeConfigurationViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ThemeConfigurationScreen(
                uiState = uiState,
                onExecuteIntent = viewModel::executeIntent,
            )
        }
        entry<SecurityConfigurationNavKey> { _ ->
            val viewModel = hiltViewModel<SecurityConfigurationViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            SecurityConfigurationScreen(
                uiState = uiState,
                onExecuteIntent = viewModel::executeIntent,
            )
        }
    }
}
