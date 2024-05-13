package com.composetest.feature.home.ui.home2

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.router.navigation.home.Home2Destination
import com.composetest.router.navigation.home.HomeDestination
import com.composetest.router.navigation.home.navtypes.InnerHome
import com.composetest.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Home2ViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider
) : BaseViewModel<Home2Event, Home2State>(Home2State()) {

    init {
        val e = navigationProvider.getParam<Home2Destination>()
        updateState { it.copy(t = e.teste) }
    }

    override fun handleEvent(event: Home2Event) = when (event) {
        is Home2Event.ReturnHome -> navigateToLogin()
    }

    private fun navigateToLogin() {
        navigationProvider.navigateToBack(
            HomeDestination("teste", InnerHome("rer $count", "343"))
        )
        count++
    }

    companion object {
        var count = 1
    }
}