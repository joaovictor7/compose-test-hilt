package com.composetest.feature.configuration.ui.configuration

import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.interfaces.BaseUiEvent

internal sealed interface ConfigurationUiEvent : BaseUiEvent {
    data class NavigateTo(val navigateModel: NavigationModel) : ConfigurationUiEvent
}
