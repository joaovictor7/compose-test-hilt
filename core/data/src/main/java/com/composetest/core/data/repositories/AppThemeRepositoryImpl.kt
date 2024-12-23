package com.composetest.core.data.repositories

import com.composetest.core.data.constants.PreferencesDataKeys
import com.composetest.core.data.datasources.local.AppThemeDataSource
import com.composetest.core.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.mappers.AppThemeMapper
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.AppThemeModel
import com.composetest.core.domain.repositories.AppThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class AppThemeRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource,
    private val appThemeDataSource: AppThemeDataSource,
    private val appThemeMapper: AppThemeMapper,
) : AppThemeRepository {

    override val customAppThemeFlow: Flow<Theme?>
        get() = appThemeDataSource.customAppThemeFlow

    override val appThemeFlow: Flow<AppThemeModel>
        get() = preferenceDataSource.getData { preferences ->
            val theme = preferences[PreferencesDataKeys.appTheme]
            val dynamicColors = preferences[PreferencesDataKeys.dynamicColor]
            appThemeMapper(theme, dynamicColors)
        }

    override suspend fun setTheme(theme: Theme) {
        preferenceDataSource.setData(PreferencesDataKeys.appTheme, theme.name)
    }

    override suspend fun setDynamicColor(dynamicColor: Boolean) {
        preferenceDataSource.setData(PreferencesDataKeys.dynamicColor, dynamicColor)
    }

    override fun setCustomTheme(customTheme: Theme?) {
        appThemeDataSource.emitCustomAppTheme(customTheme)
    }
}