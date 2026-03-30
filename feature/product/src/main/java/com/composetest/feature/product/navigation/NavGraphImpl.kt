package com.composetest.feature.product.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navkey.product.ProductListNavKey
import com.composetest.feature.product.navigation.navkey.ProductDetailNavKey
import com.composetest.feature.product.presenter.ui.form.ProductDetailScreen
import com.composetest.feature.product.presenter.ui.form.viewmodel.ProductDetailViewModel
import com.composetest.feature.product.presenter.ui.list.ProductListScreen
import com.composetest.feature.product.presenter.ui.list.viewmodel.ProductListViewModel
import javax.inject.Inject

internal class NavGraphImpl @Inject constructor() : NavGraph {
    override fun EntryProviderScope<NavKey>.registerEntries(navBackStack: NavBackStack<NavKey>) {
        entry<ProductListNavKey> { _ ->
            val viewModel = hiltViewModel<ProductListViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ProductListScreen(
                uiState = uiState,
                uiEvent = viewModel.uiEvent,
                onExecuteIntent = viewModel::executeIntent,
                navBackStack = navBackStack,
            )
        }
        entry<ProductDetailNavKey> { _ ->
            val viewModel = hiltViewModel<ProductDetailViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ProductDetailScreen(uiState = uiState)
        }
    }
}
