package com.composetest.feature.home.ui.home

import com.composetest.core.domain.managers.AppThemeManager
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.usecases.AnalyticsUseCase
import com.composetest.core.router.destinations.home.Home2Destination
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.extensions.getParam
import com.composetest.core.router.extensions.getResultFlow
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.router.providers.NavHostControllerProvider
import com.composetest.core.router.results.home.Home2Result
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.home.ui.home.analytics.HomeAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val navHostControllerProvider: NavHostControllerProvider,
    private val appThemeManager: AppThemeManager,
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<HomeUiState>(HomeAnalytic, HomeUiState()), HomeCommandReceiver {

    override val commandReceiver = this

    init {
        openScreenAnalytic()
        val e = navigationManager.getParam<HomeDestination>()
        updateUiState { it.copy(t = e.innerHome.teste) }
        teste()
    }

    override fun navigateToHome2() {
        navigationManager.navigate(Home2Destination("tessfdf", "rer"))
    }

    override fun changeAppTheme(
        theme: Theme?,
        dynamicColors: Boolean?
    ) {
        runAsyncTask {
            when {
                theme != null -> appThemeManager.setTheme(theme)
                dynamicColors != null -> appThemeManager.setDynamicColor(dynamicColors)
            }
        }
    }

    private fun teste() {
        runFlowTask(flow = navHostControllerProvider.getResultFlow<Home2Result>()) {
            val e = it
        }
    }
}