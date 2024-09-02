package com.composetest.feature.home.ui.home2

import com.composetest.core.ui.interfaces.BaseUiState

internal data class Home2UiState(
    val t: String = " String(2)",
    val dockHeight: Int = 0
) : BaseUiState {
    fun setDockHeight(height: Int) = copy(dockHeight = height)
}
