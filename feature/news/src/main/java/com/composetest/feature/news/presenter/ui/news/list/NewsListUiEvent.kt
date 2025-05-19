package com.composetest.feature.news.presenter.ui.news.list

import com.composetest.core.router.model.NavigationModel

internal sealed interface NewsListUiEvent {
    data class NavigateTo(val navigationModel: NavigationModel) : NewsListUiEvent
}
