package com.composetest.core.data.datasources.local

import com.composetest.core.domain.enums.Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class AppThemeDataSource @Inject constructor() {

    private val _customAppThemeFlow = MutableStateFlow<Theme?>(null)
    val customAppThemeFlow = _customAppThemeFlow

    fun emitCustomAppTheme(theme: Theme?) {
        _customAppThemeFlow.update { theme }
    }
}