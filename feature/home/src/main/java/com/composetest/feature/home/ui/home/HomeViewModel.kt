package com.composetest.feature.home.ui.home

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.home.Home2Destination
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.destinations.home.InnerHome2
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.extensions.getParam
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.router.results.home.Home2Result
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.home.ui.home.analytics.HomeAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    @NavGraphQualifier(NavGraph.ROOT) private val navigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<HomeUiState>(HomeAnalytic, HomeUiState()), HomeCommandReceiver {

    override val commandReceiver = this

    init {
        openScreenAnalytic()
        val e = navigationManager.getParam<HomeDestination>()
        teste()
    }

    override fun navigateToHome2() {
        navigationManager.navigate(
            Home2Destination(
                "tessfdf",
                "rer",
                InnerHome2("teste", "te")
            )
        )
    }

    private fun teste() {
        runFlowTask(flow = navigationManager.getResultFlow(Home2Result::class)) {
            val e = it
        }
    }
}