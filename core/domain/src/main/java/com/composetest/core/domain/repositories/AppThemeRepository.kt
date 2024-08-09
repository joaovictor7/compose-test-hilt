package com.composetest.core.domain.repositories

import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.AppThemeModel
import kotlinx.coroutines.flow.Flow

interface AppThemeRepository {
    val customAppTheme: Flow<Theme?>

    suspend fun setTheme(theme: Theme)
    suspend fun setDynamicColor(dynamicColor: Boolean)
    fun setCustomTheme(customTheme: Theme?)
    fun getAppTheme(): Flow<AppThemeModel>
}