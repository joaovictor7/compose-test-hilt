package com.composetest.feature.home.ui.home2

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.home.Home2Destination
import com.composetest.core.router.destinations.home.Home3Destination
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.extensions.getParam
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.home.ui.home2.analytics.Home2Analytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class Home2ViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<Home2UiState>(Home2Analytic, Home2UiState()), Home2CommandReceiver {

    override val commandReceiver = this

    init {
        openScreenAnalytic()
        val e = navigationManager.getParam<Home2Destination>()
        updateUiState { it.copy(t = e.teste) }
    }

    override fun returnHome() {
        navigationManager.navigate(
            Home3Destination("teste", "teste"),
            NavigationMode.REMOVE_CURRENT_SCREEN
        )
        count++
    }

    private companion object {
        var count = 1
    }
}