package com.composetest.feature.root.ui.root

import androidx.navigation.NavHostController
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.configuration.ConfigurationDestination
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.destinations.profile.ProfileDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.managers.NavControllerManager
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.root.analytics.root.RootAnalytic
import com.composetest.feature.root.enums.NavigationBottomBar
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class RootViewModel @Inject constructor(
    private val navControllerManager: NavControllerManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.ROOT) override val navigationManager: NavigationManager
) : BaseViewModel<RootUiState>(RootAnalytic, RootUiState()), RootCommandReceiver {

    private val bottomBarItemsNavigation = mutableListOf(firstSelectedBottomBarItem)

    override val commandReceiver = this

    override fun initUiState() {
        openScreenAnalytic()
    }

    override fun backHandler() {
        if (bottomBarItemsNavigation.size > 1) {
            val bottomBarItem = bottomBarItemsNavigation[bottomBarItemsNavigation.lastIndex.dec()]
            bottomBarItemsNavigation.removeLastOrNull()
            navigateToBottomBarItem(bottomBarItem)
        } else {
            updateUiState { it.copy(finishApp = true) }
        }
    }

    override fun setSelectedNavigationBottomBar(selectedBottomBarItem: NavigationBottomBar) {
        if (firstSelectedBottomBarItem == selectedBottomBarItem) {
            bottomBarItemsNavigation.clear()
        } else {
            bottomBarItemsNavigation.remove(selectedBottomBarItem)
        }
        bottomBarItemsNavigation.add(selectedBottomBarItem)
        navigateToBottomBarItem(selectedBottomBarItem)
    }

    override fun setRootNavGraph(navController: NavHostController) {
        navControllerManager.setNavController(NavGraph.ROOT, navController)
        currentScreenObservable()
    }

    private fun navigateToBottomBarItem(bottomBarItem: NavigationBottomBar) {
        val destination = when (bottomBarItem) {
            NavigationBottomBar.HOME -> HomeDestination
            NavigationBottomBar.PROFILE -> ProfileDestination
            NavigationBottomBar.CONFIGURATION -> ConfigurationDestination
        }
        navigationManager.navigate(destination, NavigationMode.SAVE_SCREEN_STATE)
    }

    private fun currentScreenObservable() {
        runFlowTask(navigationManager.currentRouteChangesFlow) { currentRoute ->
            val bottomBarItem = when (currentRoute) {
                HomeDestination.asRoute -> NavigationBottomBar.HOME
                ProfileDestination.asRoute -> NavigationBottomBar.PROFILE
                ConfigurationDestination.asRoute -> NavigationBottomBar.CONFIGURATION
                else -> null
            }
            if (bottomBarItem != null) {
                updateUiState { it.setSelectedBottomBarItem(bottomBarItem) }
                return@runFlowTask
            }
        }
    }

    internal companion object {
        val firstSelectedBottomBarItem = NavigationBottomBar.HOME
    }
}