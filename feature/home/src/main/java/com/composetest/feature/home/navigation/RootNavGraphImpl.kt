package com.composetest.feature.home.navigation

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navkey.home.HomeNavKey
import com.composetest.feature.home.presenter.ui.home.HomeScreen
import com.composetest.feature.home.presenter.ui.home.viewmodel.HomeViewModel
import javax.inject.Inject

internal class RootNavGraphImpl @Inject constructor() : NavGraph {
    override fun EntryProviderScope<NavKey>.registerEntries(navBackStack: NavBackStack<NavKey>) {
        entry<HomeNavKey> { _ ->
            hiltViewModel<HomeViewModel>()
            HomeScreen()
        }
    }
}
