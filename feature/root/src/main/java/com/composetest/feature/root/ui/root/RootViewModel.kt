package com.composetest.feature.root.ui.root

import com.composetest.core.domain.managers.SessionManager
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.domain.usecases.remoteconfigs.GetAvailableFeaturesUseCase
import com.composetest.core.domain.usecases.user.GetUserUseCase
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.feature.root.analytics.root.RootEventAnalytic
import com.composetest.feature.root.analytics.root.RootScreenAnalytic
import com.composetest.feature.root.enums.NavigationFeature
import com.composetest.feature.root.enums.NavigationLocal
import com.composetest.feature.root.mappers.UserModalDrawerMapper
import com.composetest.feature.root.models.BottomFeatureNavigationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class RootViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val getUserUseCase: GetUserUseCase,
    private val userModalDrawerMapper: UserModalDrawerMapper,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    getAvailableFeaturesUseCase: GetAvailableFeaturesUseCase,
) : BaseViewModel(), UiState<RootUiState>, UiEvent<RootUiEvent>, RootCommandReceiver {

    private val _uiState = MutableStateFlow(RootUiState())
    private val _uiEvent = MutableSharedFlow<RootUiEvent>()
    private val availableFeatures = getAvailableFeaturesUseCase()
    private val bottomNavigationFeaturesOrder = mutableListOf<NavigationFeature>()
    private var firstBottomNavigationFeature: NavigationFeature? = null

    override val uiState = _uiState.asStateFlow()
    override val uiEvent = _uiEvent.asSharedFlow()
    override val commandReceiver = this
    override val analyticScreen = RootScreenAnalytic

    init {
        openScreenAnalytic()
        iniUiState()
    }

    override fun backHandler() {
        if (bottomNavigationFeaturesOrder.size == 1) {
            _uiEvent.emitEvent(RootUiEvent.FinishApp)
            return
        }
        val navigationBottomFeature =
            bottomNavigationFeaturesOrder[bottomNavigationFeaturesOrder.lastIndex.dec()]
        bottomNavigationFeaturesOrder.removeLastOrNull()
        _uiEvent.emitEvent(
            RootUiEvent.NavigateToBottomFeature(
                NavigationModel(
                    navigationBottomFeature.destination,
                    NavigationMode.SAVE_SCREEN_STATE
                )
            )
        )
    }

    override fun navigateToFeature(navigationFeature: NavigationFeature) {
        sendNavigateToFeatureAnalytic(navigationFeature)
        if (navigationFeature.navigationLocal == NavigationLocal.MODAL_DRAWER) {
            navigateToModalDrawerFeature(navigationFeature)
        } else {
            navigateToBottomFeature(navigationFeature)
        }
    }

    override fun logout() {
        runAsyncTask {
            sessionManager.finishCurrentSession()
            _uiEvent.emitEvent(
                RootUiEvent.NavigateToFeature(
                    NavigationModel(
                        destination = LoginDestination(true),
                        navigationMode = NavigationMode.REMOVE_ALL_SCREENS_STACK
                    ),
                )
            )
        }
    }

    override fun currentScreenObservable(currentRoute: String?) {
        val bottomNavigationFeature = NavigationFeature.bottomNavigationFeatures
            .firstOrNull { currentRoute == it.destination.asRoute }
        bottomNavigationFeature?.let {
            _uiState.update { it.setSelectedBottomNavigationFeature(bottomNavigationFeature) }
        }
    }

    private fun iniUiState() {
        val modalDrawerNavigationFeatures = getModalDrawerNavigationFeatures()
        val bottomNavigationFeatures = getBottomNavigationFeatures()
        runAsyncTask {
            val user = getUserUseCase()
            _uiState.update {
                it.initUiState(
                    firstBottomNavigationFeature?.destination,
                    modalDrawerNavigationFeatures,
                    bottomNavigationFeatures,
                    userModalDrawerMapper.mapperToModel(user)
                )
            }
        }
    }

    private fun navigateToBottomFeature(navigationFeature: NavigationFeature) {
        if (firstBottomNavigationFeature == navigationFeature) {
            bottomNavigationFeaturesOrder.clear()
        } else {
            bottomNavigationFeaturesOrder.remove(navigationFeature)
        }
        bottomNavigationFeaturesOrder.add(navigationFeature)
        _uiEvent.emitEvent(
            RootUiEvent.NavigateToBottomFeature(
                NavigationModel(navigationFeature.destination, NavigationMode.SAVE_SCREEN_STATE)
            )
        )
    }

    private fun navigateToModalDrawerFeature(feature: NavigationFeature) {
        _uiEvent.emitEvent(RootUiEvent.NavigateToFeature(NavigationModel(destination = feature.destination)))
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