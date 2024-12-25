package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.AppThemeDataSource
import com.composetest.core.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.mappers.AppThemeMapper
import com.composetest.core.data.preferencesdatastore.PreferencesDataKeys
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.AppThemeModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class AppThemeRepository @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource,
    private val appThemeDataSource: AppThemeDataSource,
    private val appThemeMapper: AppThemeMapper,
) {

    val customAppThemeFlow: Flow<Theme?>
        get() = appThemeDataSource.customAppThemeFlow

    val appThemeFlow: Flow<AppThemeModel>
        get() = preferenceDataSource.getData { preferences ->
            val theme = preferences[PreferencesDataKeys.appTheme]
            val dynamicColors = preferences[PreferencesDataKeys.dynamicColor]
            appThemeMapper(theme, dynamicColors)
        }

    suspend fun setTheme(theme: Theme) {
        preferenceDataSource.setData(PreferencesDataKeys.appTheme, theme.name)
    }

    suspend fun setDynamicColor(dynamicColor: Boolean) {
        preferenceDataSource.setData(PreferencesDataKeys.dynamicColor, dynamicColor)
    }

    fun setCustomTheme(customTheme: Theme?) {
        appThemeDataSource.emitCustomAppTheme(customTheme)
    }
}