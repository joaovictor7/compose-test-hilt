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
import com.composetest.feature.root.enums.NavigationLocal
import com.composetest.feature.root.mappers.UserModalDrawerMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class RootViewModel @Inject constructor(
    private val navControllerManager: NavControllerManager,
    private val getUserUseCase: GetUserUseCase,
    private val getAvailableFeaturesUseCase: GetAvailableFeaturesUseCase,
    private val userModalDrawerMapper: UserModalDrawerMapper,
    @NavGraphQualifier(NavGraph.MAIN) private val mainNavigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.ROOT) override val navigationManager: NavigationManager
) : BaseViewModel<RootUiState>(RootAnalytic, RootUiState()), RootCommandReceiver {

    private val bottomBarItemsNavigation = mutableListOf(firstSelectedBottomBarItem)

    override val commandReceiver = this

    override fun initUiState() {
        openScreenAnalytic()
        runAsyncTask {
            val navigationFeatures = getNavigationFeatures()
            val user = getUserUseCase()
            updateUiState { it.initUiState(navigationFeatures, userModalDrawerMapper(user)) }
        }
    }

    override fun backHandler() {
        if (bottomBarItemsNavigation.size > 1) {
            val bottomBarItem = bottomBarItemsNavigation[bottomBarItemsNavigation.lastIndex.dec()]
            bottomBarItemsNavigation.removeLastOrNull()
            navigateToFeature(bottomBarItem, NavigationLocal.BOTTOM)
        } else {
            updateUiState { it.copy(finishApp = true) }
        }
    }

    override fun setSelectedNavigationFeature(
        selectedFeature: NavigationFeature,
        navigationLocal: NavigationLocal
    ) {
        when (navigationLocal) {
            NavigationLocal.BOTTOM -> {
                if (firstSelectedBottomBarItem == selectedFeature) {
                    bottomBarItemsNavigation.clear()
                } else {
                    bottomBarItemsNavigation.remove(selectedFeature)
                }
                bottomBarItemsNavigation.add(selectedFeature)
            }
            NavigationLocal.MODAL_DRAWER -> {
                val newList = uiState.value.navigationFeatures.map {
                    it.apply {
                        if (this == selectedFeature) selected = true
                    }
                }
                updateUiState { it.setNavigationFeatures(features = newList) }
            }
        }
        navigateToFeature(selectedFeature, navigationLocal)
    }

    override fun setRootNavGraph(navController: NavHostController) {
        navControllerManager.setNavController(NavGraph.ROOT, navController)
        currentScreenObservable()
    }

    private fun navigateToFeature(feature: NavigationFeature, navigationLocal: NavigationLocal) {
        when (navigationLocal) {
            NavigationLocal.BOTTOM ->
                navigationManager.navigate(feature.destination, NavigationMode.SAVE_SCREEN_STATE)
            NavigationLocal.MODAL_DRAWER -> mainNavigationManager.navigate(feature.destination)
        }
    }

    private fun currentScreenObservable() {
        runFlowTask(navigationManager.currentRouteChangesFlow) { currentRoute ->
            val bottomNavigationFeature = when (currentRoute) {
                HomeDestination.asRoute -> NavigationFeature.HOME
                else -> null
            }
            if (bottomNavigationFeature == null) return@runFlowTask
            val newList = uiState.value.navigationFeatures.map {
                it.apply {
                    if (this == bottomNavigationFeature) selected = true
                }
            }
            updateUiState { it.setNavigationFeatures(features = newList) }
        }
    }

    private fun getNavigationFeatures(): List<NavigationFeature> {
        val features = getAvailableFeaturesUseCase()
        return NavigationFeature.entries.filter { it.feature in features }
    }

    internal companion object {
        val firstSelectedBottomBarItem = NavigationFeature.HOME
    }
}