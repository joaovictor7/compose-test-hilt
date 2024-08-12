package com.composetest.feature.home.ui.home

import androidx.navigation.NavHostController
import com.composetest.core.ui.interfaces.BaseUiState

internal data class HomeUiState(
    val navHostController: NavHostController? = null
) : BaseUiState
