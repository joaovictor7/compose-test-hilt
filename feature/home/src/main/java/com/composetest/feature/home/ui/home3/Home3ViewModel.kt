package com.composetest.feature.home.ui.home3

import com.composetest.common.abstracts.BaseViewModel
import com.composetest.common.di.qualifiers.IoDispatcher
import com.composetest.core.router.extensions.getParam
import com.composetest.core.router.destinations.home.Home2Destination
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
internal class Home3ViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider,
    @IoDispatcher override val dispatcher: CoroutineDispatcher
) : BaseViewModel<Home3UiState>(Home3UiState()), Home3CommandReceiver {

    init {
        val e = navigationProvider.getParam<Home2Destination>()
        updateUiState { it.copy(t = e.teste) }
    }

    override fun returnLogin() {
        navigationProvider.navigateToBack(LoginDestination)
        count++
    }

    private companion object {
        var count = 1
    }
}