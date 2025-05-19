package com.composetest.feature.configuration.presenter.ui.configuration

import com.composetest.core.router.model.NavigationModel

internal sealed interface ConfigurationUiEvent {
    data class NavigateTo(val navigateModel: NavigationModel) : ConfigurationUiEvent
}
