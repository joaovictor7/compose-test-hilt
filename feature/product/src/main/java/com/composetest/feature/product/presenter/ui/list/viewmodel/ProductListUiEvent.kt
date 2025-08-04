package com.composetest.feature.product.presenter.ui.list.viewmodel

import com.composetest.core.router.model.NavigationModel

internal sealed interface ProductListUiEvent {
    data class NavigateTo(val navigationModel: NavigationModel) : ProductListUiEvent
}
