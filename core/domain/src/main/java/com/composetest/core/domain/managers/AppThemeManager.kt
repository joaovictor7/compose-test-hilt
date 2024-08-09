package com.composetest.core.domain.managers

import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.AppThemeModel
import kotlinx.coroutines.flow.Flow

interface AppThemeManager {

    fun getAppTheme(): Flow<AppThemeModel>

    suspend fun setTheme(theme: Theme)

    suspend fun setDynamicColor(dynamicColor: Boolean)

    fun setCustomTheme(customTheme: Theme?)
}