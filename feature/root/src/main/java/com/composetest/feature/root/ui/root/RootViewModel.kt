package com.composetest.feature.root.ui.root

import androidx.navigation.NavHostController
import com.composetest.core.domain.usecases.GetAvailableFeaturesUseCase
import com.composetest.core.domain.usecases.GetUserUseCase
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.managers.NavControllerManager
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.root.analytics.root.RootAnalytic
import com.composetest.feature.root.enums.NavigationFeature
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class RootViewModel @Inject constructor(
    private val navControllerManager: NavControllerManager,
    private val getUserUseCase: GetUserUseCase,
    private val getAvailableFeaturesUseCase: GetAvailableFeaturesUseCase,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.ROOT) override val navigationManager: NavigationManager
) : BaseViewModel<RootUiState>(RootAnalytic, RootUiState()), RootCommandReceiver {

    private val bottomBarItemsNavigation = mutableListOf(firstSelectedBottomBarItem)

    override val commandReceiver = this

    override fun initUiState() {
        openScreenAnalytic()
        val navigationFeatures = getNavigationFeatures()
        updateUiState { it.initUiState(navigationFeatures) }
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

    override fun setSelectedBottomNavigationFeature(selectedBottomBarItem: NavigationFeature) {
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

    private fun navigateToBottomBarItem(bottomBarItem: NavigationFeature) {
        navigationManager.navigate(bottomBarItem.destination, NavigationMode.SAVE_SCREEN_STATE)
    }

    private fun currentScreenObservable() {
        runFlowTask(navigationManager.currentRouteChangesFlow) { currentRoute ->
            val bottomBarItem = when (currentRoute) {
                HomeDestination.asRoute -> NavigationFeature.HOME
                else -> null
            }
            if (bottomBarItem != null) {
                updateUiState { it.setSelectedBottomNavigationFeature(bottomBarItem) }
                return@runFlowTask
            }
        }
    }

    private fun getNavigationFeatures(): List<NavigationFeature> {
        val features = getAvailableFeaturesUseCase()
        return NavigationFeature.entries.filter { it.feature in features }
    }

    private fun getUserData() {
        runAsyncTask {
            val user = getUserUseCase()
            uiState
        }
    }

    internal companion object {
        val firstSelectedBottomBarItem = NavigationFeature.HOME
    }
}