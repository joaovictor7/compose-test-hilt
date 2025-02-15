package com.composetest.feature.configuration.ui.configuration

import com.composetest.core.router.models.NavigationModel

internal sealed interface ConfigurationUiEvent {
    data class NavigateTo(val navigateModel: NavigationModel) : ConfigurationUiEvent
}
