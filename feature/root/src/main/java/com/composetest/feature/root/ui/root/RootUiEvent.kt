package com.composetest.feature.root.ui.root

import androidx.compose.material3.DrawerValue
import com.composetest.core.ui.interfaces.BaseUiEvent

internal sealed interface RootUiEvent : BaseUiEvent {
    data object FinishApp : RootUiEvent
    data class ManagerModalDrawer(val drawerValue: DrawerValue) : RootUiEvent
}