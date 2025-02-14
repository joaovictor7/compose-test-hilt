package com.composetest.feature.root.ui.root

import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.interfaces.BaseUiEvent

internal sealed interface RootUiEvent : BaseUiEvent {
    data object FinishApp : RootUiEvent
    data class NavigateToFeature(val navigationModel: NavigationModel) : RootUiEvent
    data class NavigateToBottomFeature(val navigationModel: NavigationModel) : RootUiEvent
}