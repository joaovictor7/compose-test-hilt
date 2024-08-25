package com.composetest.feature.root.ui

import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.composetest.core.designsystem.components.dock.params.IconDockParam
import com.composetest.core.domain.managers.RootDockManager
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.configuration.ConfigurationRootDestination
import com.composetest.core.router.destinations.home.HomeRootDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.managers.NavControllerManager
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.root.enums.DockItem
import com.composetest.feature.root.ui.analytics.RootAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class RootViewModel @Inject constructor(
    private val navControllerManager: NavControllerManager,
    private val rootDockManager: RootDockManager,
    @NavGraphQualifier(NavGraph.ROOT) private val navigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<RootUiState>(RootAnalytic, RootUiState()), RootCommandReceiver {

    private val selectedDockItem = MutableStateFlow(DockItem.HOME)

    override val commandReceiver = this

    init {
        setDockItems()
        dockVisibilityObservable()
        dockChangesObservable()
    }

    override fun changeSelectedDockItem(selectedIndex: Int) {
        selectedDockItem.update { DockItem.getItemDock(selectedIndex) }
    }

    override fun setRootNavGraph(navController: NavHostController) {
        navControllerManager.setNavController(NavGraph.ROOT, navController)
        currentScreenObservable()
    }

    private fun dockChangesObservable() {
        runFlowTask(selectedDockItem) { dockItem ->
            updateUiState { it.setSelectedDockItem(DockItem.entries.indexOf(dockItem)) }
            navigateToDockItem(dockItem)
        }
    }

    private suspend fun navigateToDockItem(dockItem: DockItem) = when (dockItem) {
        DockItem.HOME -> navigationManager.asyncNavigate(
            HomeRootDestination,
            NavigationMode.NESTED_NAVIGATION
        )

        DockItem.CONFIGURATION -> navigationManager.asyncNavigate(
            ConfigurationRootDestination,
            NavigationMode.NESTED_NAVIGATION
        )
    }

    private fun setDockItems() {
        val iconDockParam = DockItem.entries.mapIndexed { index, value ->
            IconDockParam(
                index = index,
                iconId = value.iconId
            )
        }
        updateUiState { it.setDockItems(iconDockParam) }
    }

    private fun currentScreenObservable() = with(navigationManager) {
        runFlowTask(currentScreenFlow) { currentScreen ->
            currentScreen.hierarchy.forEach {
                val dockItem = when (it) {
                    getNavDestination(HomeRootDestination) -> DockItem.HOME
                    getNavDestination(ConfigurationRootDestination) -> DockItem.CONFIGURATION
                    else -> null
                }
                if (dockItem != null) {
                    selectedDockItem.update { dockItem }
                    return@forEach
                }
            }
        }
    }

    private fun dockVisibilityObservable() {
        runFlowTask(rootDockManager.dockVisibilityFlow) { visible ->
            updateUiState { it.copy(dockVisible = visible) }
        }
    }
}