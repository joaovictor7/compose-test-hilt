package com.composetest.feature.form.presenter.ui.form.viewmodel

import com.composetest.core.router.model.NavigationModel

internal sealed interface FormUiEvent {
    data class NavigateTo(val navigationModel: NavigationModel) : FormUiEvent
}