package com.composetest.feature.home.ui.home

import com.composetest.core.domain.usecases.rootdock.GetRootDockHeightUseCase
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.home.ui.home.analytics.HomeAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getRootDockHeightUseCase: GetRootDockHeightUseCase,
    @NavGraphQualifier(NavGraph.ROOT) private val navigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<HomeUiState>(HomeAnalytic, HomeUiState()), HomeCommandReceiver {

    override val commandReceiver = this

    init {
        openScreenAnalytic()
        dockHeightObservable()
    }

    override fun navigateToHome2() {
//        navigationManager.navigate(
//            Home2Destination(
//                "tessfdf",
//                "rer",
//                InnerHome2("teste", "te")
//            )
//        )
    }

    private fun dockHeightObservable() = runFlowTask(getRootDockHeightUseCase()) { dockHeight ->
        updateUiState { it.setDockHeight(dockHeight) }
    }
}