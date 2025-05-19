package com.composetest.feature.root.presentation.ui.root

import com.composetest.core.router.model.NavigationModel

internal sealed interface RootUiEvent {
    data object FinishApp : RootUiEvent
    data class NavigateToFeature(val navigationModel: NavigationModel) : RootUiEvent
    data class NavigateToBottomFeature(val navigationModel: NavigationModel) : RootUiEvent
}