package com.composetest.feature.exchange.navigation

import androidx.navigation.NavGraphBuilder
import com.composetest.core.router.destinations.exchange.ExchangeDetailDestination
import com.composetest.core.router.destinations.exchange.ExchangeListDestination
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.ui.extensions.buildComposable
import com.composetest.feature.exchange.ui.detail.ExchangeDetailCommandReceiver
import com.composetest.feature.exchange.ui.detail.ExchangeDetailScreen
import com.composetest.feature.exchange.ui.detail.ExchangeDetailUiEvent
import com.composetest.feature.exchange.ui.detail.ExchangeDetailUiState
import com.composetest.feature.exchange.ui.detail.ExchangeDetailViewModel
import com.composetest.feature.exchange.ui.list.ExchangeListCommandReceiver
import com.composetest.feature.exchange.ui.list.ExchangeListScreen
import com.composetest.feature.exchange.ui.list.ExchangeListUiEvent
import com.composetest.feature.exchange.ui.list.ExchangeListUiState
import com.composetest.feature.exchange.ui.list.ExchangeListViewModel

object ExchangeNavGraph : NavGraph {
    override fun NavGraphBuilder.navGraph(navigateBackHandler: Boolean) {
        buildComposable<ExchangeListDestination, ExchangeListViewModel, ExchangeListUiState, ExchangeListUiEvent, ExchangeListCommandReceiver>(
            screen = ExchangeListScreen,
            navigateBackHandler = navigateBackHandler,
        )
        buildComposable<ExchangeDetailDestination, ExchangeDetailViewModel, ExchangeDetailUiState, ExchangeDetailUiEvent, ExchangeDetailCommandReceiver>(
            screen = ExchangeDetailScreen,
            navigateBackHandler = navigateBackHandler,
        )
    }
}