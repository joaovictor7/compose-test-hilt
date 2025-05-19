package com.composetest.core.domain.repository

import com.composetest.core.domain.enums.Theme
import kotlinx.coroutines.flow.Flow

interface SystemBarsThemeRepository {
    fun getStatusBarsTheme(): Flow<Theme?>
    fun setStatusBarsTheme(theme: Theme?)
}