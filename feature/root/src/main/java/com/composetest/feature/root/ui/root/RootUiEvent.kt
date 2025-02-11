package com.composetest.feature.root.ui.root

import com.composetest.core.ui.interfaces.BaseUiEvent

internal sealed interface RootUiEvent : BaseUiEvent {
    data object FinishApp : RootUiEvent
}