package com.composetest.feature.home.ui.home3

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.home.Home2Destination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.home.ui.home3.analytics.Home3Analytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class Home3ViewModel @Inject constructor(
    @NavGraphQualifier(NavGraph.ROOT) private val navigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<Home3UiState>(Home3Analytic, Home3UiState()), Home3CommandReceiver {

    override val commandReceiver = this

    init {
        openScreenAnalytic()
//        val e = navigationManager.getParam<Home2Destination>()
//        updateUiState { it.copy(t = e.teste) }
    }

    override fun returnLogin() {
        navigationManager.navigate(Home2Destination)
        count++
    }

    private companion object {
        var count = 1
    }
}