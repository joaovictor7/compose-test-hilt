package com.composetest.feature.home.ui.home3

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.home.Home3Destination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.home.analytics.home3.Home3ScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class Home3ViewModel @Inject constructor(
    private val destination: Home3Destination,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<Home3UiState, Home3UiEvent>(Home3ScreenAnalytic, Home3UiState()), Home3CommandReceiver {

    override val commandReceiver = this

    override fun initUiState() {
        openScreenAnalytic()
        updateUiState { it.copy(t = destination.teste) }
    }

    override fun returnLogin() {
        navigationManager.navigateBack()
        count++
    }

    private companion object {
        var count = 1
    }
}