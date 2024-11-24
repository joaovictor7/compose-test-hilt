package com.composetest.core.data.datasources.local

import com.composetest.core.domain.enums.Theme
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

internal class AppThemeDataSourceImpl @Inject constructor() : AppThemeDataSource {

    private val _customAppThemeFlow = MutableSharedFlow<Theme?>()
    override val customAppThemeFlow = _customAppThemeFlow

    override suspend fun emitCustomAppTheme(theme: Theme?) {
        _customAppThemeFlow.emit(theme)
    }
}