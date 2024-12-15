package com.composetest.feature.home.ui.home3

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.home.Home3Destination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.extensions.getDestination
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.home.analytics.home3.Home3ScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class Home3ViewModel @Inject constructor(
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<Home3UiState, Home3UiEvent>(Home3ScreenAnalytic, Home3UiState()), Home3CommandReceiver {

    private val param = navigationManager.getDestination<Home3Destination>()

    override val commandReceiver = this

    override fun initUiState() {
        openScreenAnalytic()
        updateUiState { it.copy(t = param.teste) }
    }

    override fun returnLogin() {
        navigationManager.navigateBack()
        count++
    }

    private companion object {
        var count = 1
    }
}