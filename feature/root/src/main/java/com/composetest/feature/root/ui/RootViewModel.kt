package com.composetest.feature.root.ui

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
import javax.inject.Inject

@HiltViewModel
internal class RootViewModel @Inject constructor(
    private val navControllerManager: NavControllerManager,
    private val rootDockManager: RootDockManager,
    @NavGraphQualifier(NavGraph.ROOT) private val navigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<RootUiState>(RootAnalytic, RootUiState()), RootCommandReceiver {

    private val iconDockParam = DockItem.entries.mapIndexed { index, value ->
        IconDockParam(
            index = index,
            iconId = value.iconId
        )
    }

    override val commandReceiver = this

    init {
        setDockItems()
    }

    override fun changeDockItemSelected(selectedIndex: Int) {
        updateUiState { it.setSelectedDockItem(selectedIndex) }
        navigateToDockItem(DockItem.getItemDock(selectedIndex))
    }

    override fun setRootNavGraph(navController: NavHostController) {
        navControllerManager.setNavController(NavGraph.ROOT, navController)
    }

    private fun navigateToDockItem(dockItem: DockItem) = when (dockItem) {
        DockItem.HOME -> navigationManager.navigate(
            HomeRootDestination,
            NavigationMode.NESTED_NAVIGATION
        )
        DockItem.CONFIGURATION -> navigationManager.navigate(
            ConfigurationRootDestination,
            NavigationMode.NESTED_NAVIGATION
        )
    }

    private fun setDockItems() {
        updateUiState { it.setDockItems(iconDockParam) }
        runFlowTask(rootDockManager.dockVisibilityFlow) { visible ->
            updateUiState { it.copy(dockVisible = visible) }
        }
    }
}