package com.composetest.feature.root.presentation.ui.root.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.domain.usecase.navigation.ObserveNavResultUseCase
import com.composetest.core.domain.usecase.session.FinishSessionUseCase
import com.composetest.core.domain.usecase.user.GetCurrentUserUseCase
import com.composetest.core.router.di.quailifier.NavGraphListQualifier
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.model.NavigationModel
import com.composetest.core.router.navkey.login.LoginNavKey
import com.composetest.core.router.result.account.AccountUpdateResult
import com.composetest.core.ui.base.BaseViewModel
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.interfaces.UiEvent
import com.composetest.core.ui.interfaces.UiState
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.root.analytic.event.RootEventAnalytic
import com.composetest.feature.root.analytic.screen.RootScreenAnalytic
import com.composetest.feature.root.domain.usecase.GetAvailableFeaturesUseCase
import com.composetest.feature.root.navigation.di.NavGraphListModule.ROOT_NAV_GRAPH_LIST
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
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
internal class RootViewModel @Inject constructor(
    private val finishSessionUseCase: FinishSessionUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val userModalDrawerMapper: UserModalDrawerMapper,
    private val analyticSender: AnalyticSender,
    private val observeNavResultUseCase: ObserveNavResultUseCase,
    private val coroutineContext: CoroutineContext,
    @param:NavGraphListQualifier(ROOT_NAV_GRAPH_LIST) private val navGraphs: Array<NavGraph>,
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
        viewModelScope.launch(coroutineContext) {
            asyncTaskUtils.runAsyncTask {
                observeNavResultUseCase(AccountUpdateResult::class).collect {
                    updateUserData()
                }
            }
        }
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
                    navigationBottomFeature.navKey,
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
        viewModelScope.launch(coroutineContext) {
            asyncTaskUtils.runAsyncTask {
                finishSessionUseCase()
                _uiEvent.emitEvent(
                    RootUiEvent.NavigateToFeature(
                        NavigationModel(
                            navKey = LoginNavKey(true),
                            navigationMode = NavigationMode.REMOVE_ALL_SCREENS_STACK
                        ),
                    )
                )
            }
        }
    }

    override fun updateUserData() {
        viewModelScope.launch(coroutineContext) {
            asyncTaskUtils.runAsyncTask {
                val user = getCurrentUserUseCase()
                _uiState.update {
                    it.setUpdateUser(userModalDrawerMapper.mapperToModel(user))
                }
            }
        }
    }

    override fun currentScreenObservable(currentRoute: NavKey?) {
        val bottomNavigationFeature = NavigationFeature.bottomNavigationFeatures
            .firstOrNull { currentRoute == it.navKey }
        bottomNavigationFeature?.let {
            _uiState.update { it.setSelectedBottomNavigationFeature(bottomNavigationFeature) }
        }
    }

    private fun iniUiState() {
        val modalDrawerNavigationFeatures = getModalDrawerNavigationFeatures()
        val bottomNavigationFeatures = getBottomNavigationFeatures()
        viewModelScope.launch(coroutineContext) {
            asyncTaskUtils.runAsyncTask {
                val user = getCurrentUserUseCase()
                _uiState.update {
                    it.initUiState(
                        firstBottomNavigationFeature?.navKey,
                        navGraphs,
                        modalDrawerNavigationFeatures,
                        bottomNavigationFeatures,
                        userModalDrawerMapper.mapperToModel(user)
                    )
                }
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
                NavigationModel(navigationFeature.navKey, NavigationMode.SAVE_SCREEN_STATE)
            )
        )
    }

    private fun navigateToModalDrawerFeature(feature: NavigationFeature) {
        _uiEvent.emitEvent(RootUiEvent.NavigateToFeature(NavigationModel(navKey = feature.navKey)))
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
        viewModelScope.launch(coroutineContext) {
            asyncTaskUtils.runAsyncTask {
                analyticSender.sendEvent(RootEventAnalytic.NavigateToFeature(navigationFeature.feature.name))
            }
        }
    }
}
