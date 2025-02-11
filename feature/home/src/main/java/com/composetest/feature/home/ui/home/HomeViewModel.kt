package com.composetest.feature.home.ui.home

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.home.analytics.home.HomeScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<HomeUiState, HomeUiEvent>(HomeUiState), HomeCommandReceiver {

    override val commandReceiver = this
    override val analyticScreen = HomeScreenAnalytic

    init {
        openScreenAnalytic()
    }
}