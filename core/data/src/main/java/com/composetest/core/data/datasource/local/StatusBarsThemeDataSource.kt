package com.composetest.core.data.datasource.local

import com.composetest.core.domain.enums.Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class StatusBarsThemeDataSource @Inject constructor() {

    private val _statusBarsTheme = MutableStateFlow<Theme?>(null)
    val statusBarsTheme = _statusBarsTheme.asStateFlow()

    fun setStatusBarsTheme(theme: Theme?) {
        _statusBarsTheme.update { theme }
    }
}