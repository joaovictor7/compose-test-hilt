package com.composetest.feature.news.ui.news.list

import com.composetest.core.router.models.NavigationModel

internal sealed interface NewsListUiEvent {
    data class NavigateTo(val navigationModel: NavigationModel) : NewsListUiEvent
}
