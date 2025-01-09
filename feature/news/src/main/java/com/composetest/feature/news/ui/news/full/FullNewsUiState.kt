package com.composetest.feature.news.ui.news.full

import com.composetest.core.ui.interfaces.BaseUiState

internal data class FullNewsUiState(
    val imageUrl: String? = null,
    val title: String = String(),
    val description: String? = null,
    val content: String? = null
) : BaseUiState
