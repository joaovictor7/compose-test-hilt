package com.composetest.feature.news.ui.news.list

import com.composetest.core.router.models.NavigationModel
import com.composetest.core.ui.interfaces.BaseUiEvent

internal sealed interface NewsListUiEvent : BaseUiEvent {
    data class NavigateTo(val navigationModel: NavigationModel) : NewsListUiEvent
}
