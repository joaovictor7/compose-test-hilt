package com.composetest.feature.home.ui.home2

import com.composetest.core.domain.managers.RootDockManager
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.router.results.home.Home2Result
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.home.ui.home2.analytics.Home2Analytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class Home2ViewModel @Inject constructor(
    private val rootDockManager: RootDockManager,
    @NavGraphQualifier(NavGraph.ROOT) private val navigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<Home2UiState>(Home2Analytic, Home2UiState()), Home2CommandReceiver {

    override val commandReceiver = this

    init {
        openScreenAnalytic()
//        val e = navigationManager.getParam<Home2Destination>()
//        updateUiState { it.copy(t = e.teste) }
    }

    override fun returnHome() {
//        navigationManager.navigate(
//            Home3Destination("teste", "teste"),
//            NavigationMode.REMOVE_CURRENT_SCREEN
//        )
        navigationManager.navigateBack(Home2Result("teste"))
        count++
    }

    override fun dockVisibility(visible: Boolean) {
        rootDockManager.setRootDockVisibility(visible)
    }

    private companion object {
        var count = 1
    }
}