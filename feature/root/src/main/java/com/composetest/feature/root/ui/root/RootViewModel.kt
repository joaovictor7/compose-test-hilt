package com.composetest.feature.root.ui.root

import androidx.compose.material3.DrawerValue
import androidx.navigation.NavHostController
import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.usecases.GetAvailableFeaturesUseCase
import com.composetest.core.domain.usecases.GetUserUseCase
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.managers.NavControllerManager
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.root.analytics.root.RootEventAnalytic
import com.composetest.feature.root.analytics.root.RootScreenAnalytic
import com.composetest.feature.root.enums.NavigationFeature
import com.composetest.feature.root.enums.NavigationLocal
import com.composetest.feature.root.mappers.UserModalDrawerMapper
import com.composetest.feature.root.models.BottomFeatureNavigationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class RootViewModel @Inject constructor(
    private val navControllerManager: NavControllerManager,
    private val sessionManager: SessionManager,
    private val getUserUseCase: GetUserUseCase,
    private val userModalDrawerMapper: UserModalDrawerMapper,
    @NavGraphQualifier(NavGraph.MAIN) private val mainNavigationManager: NavigationManager,
    @NavGraphQualifier(NavGraph.ROOT) private val rootNavigationManager: NavigationManager,
    getAvailableFeaturesUseCase: GetAvailableFeaturesUseCase,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
) : BaseViewModel<RootUiState, RootUiEvent>(RootUiState()), RootCommandReceiver {

    private val availableFeatures = getAvailableFeaturesUseCase()
    private val bottomNavigationFeaturesOrder = mutableListOf<NavigationFeature>()
    private var firstBottomNavigationFeature: NavigationFeature? = null

    override val commandReceiver = this
    override val analyticScreen = RootScreenAnalytic

    override fun initUiState() {
        openScreenAnalytic()
        val modalDrawerNavigationFeatures = getModalDrawerNavigationFeatures()
        val bottomNavigationFeatures = getBottomNavigationFeatures()
        runAsyncTask {
            val user = getUserUseCase()
            updateUiState {
                it.initUiState(
                    firstBottomNavigationFeature?.destination,
                    modalDrawerNavigationFeatures,
                    bottomNavigationFeatures,
                    userModalDrawerMapper(user)
                )
            }
        }
    }

    override fun backHandler() {
        if (bottomNavigationFeaturesOrder.size > 1) {
            val navigationBottomFeature =
                bottomNavigationFeaturesOrder[bottomNavigationFeaturesOrder.lastIndex.dec()]
            bottomNavigationFeaturesOrder.removeLastOrNull()
            rootNavigationManager.navigate(
                navigationBottomFeature.destination,
                NavigationMode.SAVE_SCREEN_STATE
            )
        } else {
            launchUiEvent(RootUiEvent.FinishApp)
        }
    }

    override fun navigateToFeature(navigationFeature: NavigationFeature) {
        sendNavigateToFeatureAnalytic(navigationFeature)
        if (navigationFeature.navigationLocal == NavigationLocal.MODAL_DRAWER) {
            navigateToModalDrawerFeature(navigationFeature)
        } else {
            navigateToBottomFeature(navigationFeature)
        }
    }

    override fun setRootNavGraph(navController: NavHostController) {
        navControllerManager.setNavController(NavGraph.ROOT, navController)
        currentScreenObservable()
    }

    override fun modalDrawerManager(drawerValue: DrawerValue) {
        launchUiEvent(RootUiEvent.ManagerModalDrawer(drawerValue))
    }

    override fun logout() {
        runAsyncTask {
            sessionManager.finishCurrentSession()
            mainNavigationManager.asyncNavigate(
                LoginDestination,
                NavigationMode.REMOVE_ALL_SCREENS_STACK
            )
        }
    }

    private fun navigateToBottomFeature(navigationFeature: NavigationFeature) {
        if (firstBottomNavigationFeature == navigationFeature) {
            bottomNavigationFeaturesOrder.clear()
        } else {
            bottomNavigationFeaturesOrder.remove(navigationFeature)
        }
        bottomNavigationFeaturesOrder.add(navigationFeature)
        rootNavigationManager.navigate(navigationFeature.destination, NavigationMode.SAVE_SCREEN_STATE)
    }

    private fun navigateToModalDrawerFeature(feature: NavigationFeature) {
        launchUiEvent(RootUiEvent.ManagerModalDrawer(DrawerValue.Closed))
        mainNavigationManager.navigate(feature.destination)
    }

    private fun currentScreenObservable() {
        runFlowTask(rootNavigationManager.currentRouteChangesFlow) { currentRoute ->
            val bottomNavigationFeature = NavigationFeature.bottomNavigationFeatures
                .firstOrNull { currentRoute == it.destination.asRoute }
            bottomNavigationFeature?.let {
                updateUiState {
                    it.setSelectedBottomNavigationFeature(bottomNavigationFeature)
                }
            }
        }
    }

    private fun getModalDrawerNavigationFeatures() = NavigationFeature.modalDrawerFeatures.filter {
        it.feature in availableFeatures
    }

    private fun getBottomNavigationFeatures(): List<BottomFeatureNavigationModel> {
        val bottomFeatures = NavigationFeature.bottomNavigationFeatures.filter {
            it.feature in availableFeatures
        }
        firstBottomNavigationFeature = bottomFeatures.firstOrNull()?.also {
            bottomNavigationFeaturesOrder.add(it)
        }
        return bottomFeatures.map {
            BottomFeatureNavigationModel(it, it == firstBottomNavigationFeature)
        }
    }

    private fun sendNavigateToFeatureAnalytic(navigationFeature: NavigationFeature) {
        runAsyncTask {
            sendAnalyticsUseCase(RootEventAnalytic.NavigateToFeature(navigationFeature.feature))
        }
    }
}