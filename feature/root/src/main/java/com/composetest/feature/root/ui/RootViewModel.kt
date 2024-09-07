package com.composetest.feature.root.ui

import androidx.navigation.NavHostController
import com.composetest.core.designsystem.components.dock.params.IconDockParam
import com.composetest.core.domain.enums.DockItem
import com.composetest.core.domain.managers.RootDockManager
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.configuration.ConfigurationDestination
import com.composetest.core.router.destinations.home.Home2Destination
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.managers.NavControllerManager
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.root.extensions.iconId
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

    override val commandReceiver = this

    init {
        setDockItems()
    }

    override fun backHandler() {
        rootDockManager.getNextDockItem()?.let {
            navigateToDockItem(it)
        } ?: run {
            updateUiState { it.copy(finishApp = true) }
        }
    }

    override fun changeSelectedDockItem(selectedIndex: Int) {
        navigateToDockItem(rootDockManager.changeSelectedDockItem(selectedIndex))
    }

    override fun setDockHeight(height: Int) {
        updateUiState { it.setDockHeight(height) }
    }

    override fun setRootNavGraph(navController: NavHostController) {
        navControllerManager.setNavController(NavGraph.ROOT, navController)
        currentScreenObservable()
    }

    private fun navigateToDockItem(dockItem: DockItem) {
        val destination = when (dockItem) {
            DockItem.HOME -> HomeDestination
            DockItem.CONFIGURATION -> ConfigurationDestination
            DockItem.HOME2 -> Home2Destination
        }
        navigationManager.navigate(
            destination,
            NavigationMode.SAVE_SCREEN_STATE
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

    private fun currentScreenObservable() {
        runFlowTask(navigationManager.currentRouteChangesFlow) { currentRoute ->
            val dockItem = when (currentRoute) {
                HomeDestination.asRoute -> DockItem.HOME
                ConfigurationDestination.asRoute -> DockItem.CONFIGURATION
                Home2Destination.asRoute -> DockItem.HOME2
                else -> null
            }
            if (dockItem != null) {
                updateUiState { it.setSelectedDockItem(DockItem.getItemDockIndex(dockItem)) }
                return@runFlowTask
            }
        }
    }
}