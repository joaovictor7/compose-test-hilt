package com.composetest.feature.root.ui

import androidx.navigation.NavHostController
import com.composetest.core.designsystem.components.dock.params.IconDockParam
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.configuration.ConfigurationMenuRootDestination
import com.composetest.core.router.destinations.home.HomeRootDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.router.providers.NavControllerProvider
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.root.enums.DockItem
import com.composetest.feature.root.ui.analytics.RootAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class RootViewModel @Inject constructor(
    private val navControllerProvider: NavControllerProvider,
    @NavGraphQualifier(NavGraph.MAIN) private val navigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<RootUiState>(RootAnalytic, RootUiState()), RootCommandReceiver {

    override val commandReceiver = this

    init {
        setDockItems()
        updateUiState {
            it.copy(navHostController = navControllerProvider.getNavController(NavGraph.MAIN))
        }
    }

    override fun changeDockItemSelected(selectedIndex: Int) {
        updateUiState {
            it.setSelectedDockItem(selectedIndex)
        }
        when (selectedIndex) {
            0 -> navigationManager.navigate(
                HomeRootDestination,
                NavigationMode.NESTED_NAVIGATION
            )
            1 -> navigationManager.navigate(
                ConfigurationMenuRootDestination,
                NavigationMode.NESTED_NAVIGATION
            )
        }
    }

//    override fun setRootNavGraph(navController: NavHostController) {
//        navControllerProvider.setNavController(NavGraph.ROOT, navController)
//    }

    private fun setDockItems() {
        updateUiState {
            it.setDockItems(DockItem.entries.map { IconDockParam(iconId = it.iconId) })
        }
    }
}