package com.composetest.feature.home.ui.home2

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.router.results.home.Home2Result
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.home.analytics.home2.Home2Analytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class Home2ViewModel @Inject constructor(
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.ROOT) override val navigationManager: NavigationManager
) : BaseViewModel<Home2UiState, Home2UiEvent>(Home2Analytic, Home2UiState()), Home2CommandReceiver {

    override val commandReceiver = this

    override fun initUiState() {
        openScreenAnalytic()
    }

    override fun returnHome() {
        navigationManager.navigateBack(Home2Result("teste"))
        count++
    }

    private companion object {
        var count = 1
    }
}