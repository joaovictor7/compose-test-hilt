package com.composetest.feature.news.ui.news.full

import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.destinations.news.FullNewsDestination
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.news.analytics.newsdetail.FullNewsScreenAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class FullNewsViewModel @Inject constructor(
    private val destination: FullNewsDestination,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<FullNewsUiState, FullNewsUiEvent>(FullNewsUiState()), FullNewsCommandReceiver {

    override val commandReceiver = this
    override val analyticScreen = FullNewsScreenAnalytic

    override fun initUiState() {
        updateUiState {
            it.copy(
                imageUrl = destination.imageUrl,
                title = destination.title,
                description = destination.description,
                content = destination.content
            )
        }
    }
}