package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.datasources.local.StatusBarsThemeDataSource
import com.composetest.core.data.mappers.ThemeMapper
import com.composetest.core.data.preferencesdatastore.PreferencesDataKeys
import com.composetest.core.domain.enums.Theme
import javax.inject.Inject

internal class ThemeRepository @Inject constructor(
    private val statusBarsThemeDataSource: StatusBarsThemeDataSource,
    private val preferenceDataSource: PreferenceDataSource,
    private val themeMapper: ThemeMapper,
) {

    val statusBarsTheme = statusBarsThemeDataSource.statusBarsTheme
    val theme = preferenceDataSource.getData { preferences ->
        val theme = preferences[PreferencesDataKeys.theme]
        val dynamicColors = preferences[PreferencesDataKeys.dynamicColor]
        themeMapper(theme, dynamicColors)
    }

    suspend fun setTheme(theme: Theme) {
        preferenceDataSource.setData(PreferencesDataKeys.theme, theme.name)
    }

    fun setStatusBarsTheme(theme: Theme?) {
        statusBarsThemeDataSource.setStatusBarsTheme(theme)
    }

    suspend fun setDynamicColor(dynamicColor: Boolean) {
        preferenceDataSource.setData(PreferencesDataKeys.dynamicColor, dynamicColor)
    }
}