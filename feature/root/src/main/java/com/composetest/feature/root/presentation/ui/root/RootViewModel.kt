package com.composetest.feature.root.presentation.ui.root

import androidx.lifecycle.viewModelScope
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.domain.usecase.session.FinishSessionUseCase
import com.composetest.core.domain.usecase.user.GetCurrentUserUseCase
import com.composetest.core.router.destination.login.LoginDestination
import com.composetest.core.router.di.quailifier.NavGraphQualifier
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.model.NavigationModel
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.root.analytic.event.RootEventAnalytic
import com.composetest.feature.root.analytic.screen.RootScreenAnalytic
import com.composetest.feature.root.domain.usecase.GetAvailableFeaturesUseCase
import com.composetest.feature.root.navigation.di.NavigationModule.Companion.ROOT_NAV_GRAPH
import com.composetest.feature.root.presentation.enums.NavigationFeature
import com.composetest.feature.root.presentation.enums.NavigationLocal
import com.composetest.feature.root.presentation.mapper.UserModalDrawerMapper
import com.composetest.feature.root.presentation.model.BottomFeatureNavigationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class RootViewModel @Inject constructor(
    private val finishSessionUseCase: FinishSessionUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val userModalDrawerMapper: UserModalDrawerMapper,
    private val analyticSender: AnalyticSender,
    @param:NavGraphQualifier(ROOT_NAV_GRAPH) private val navGraphs: Array<NavGraph>,
    @param:AsyncTaskUtilsQualifier(RootScreenAnalytic.SCREEN) private val asyncTaskUtils: AsyncTaskUtils,
    getAvailableFeaturesUseCase: GetAvailableFeaturesUseCase,
) : BaseViewModel(), UiState<RootUiState>, UiEvent<RootUiEvent>, RootIntentReceiver {

    private val availableFeatures = getAvailableFeaturesUseCase()
    private val bottomNavigationFeaturesOrder = mutableListOf<NavigationFeature>()
    private var firstBottomNavigationFeature: NavigationFeature? = null

    override val intentReceiver = this

    private val _uiState = MutableStateFlow(RootUiState())
    override val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<RootUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
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
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            finishSessionUseCase()
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

    override fun updateUserData() {
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            val user = getCurrentUserUseCase()
            _uiState.update {
                it.setUpdateUser(userModalDrawerMapper.mapperToModel(user))
            }
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
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            val user = getCurrentUserUseCase()
            _uiState.update {
                it.initUiState(
                    firstBottomNavigationFeature?.destination,
                    navGraphs,
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
        asyncTaskUtils.runAsyncTask(viewModelScope) {
            analyticSender.sendEvent(RootEventAnalytic.NavigateToFeature(navigationFeature.feature.name))
        }
    }
}