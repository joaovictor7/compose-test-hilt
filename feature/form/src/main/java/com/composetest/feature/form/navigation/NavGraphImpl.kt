package com.composetest.feature.form.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.navstack.LocalMainNavBackStack
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navkey.form.FormNavKey
import com.composetest.feature.form.presenter.ui.form.FormScreen
import com.composetest.feature.form.presenter.ui.form.viewmodel.FormViewModel
import javax.inject.Inject

internal class NavGraphImpl @Inject constructor() : NavGraph {
    override fun EntryProviderScope<NavKey>.registerEntries() {
        entry<FormNavKey> { _ ->
            val viewModel: FormViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            FormScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                onExecuteIntent = viewModel::executeIntent,
                navBackStack = LocalMainNavBackStack.current,
            )
        }
    }
}
