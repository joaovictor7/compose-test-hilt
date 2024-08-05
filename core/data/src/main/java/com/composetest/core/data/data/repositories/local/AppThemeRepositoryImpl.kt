package com.composetest.core.data.data.repositories.local

import com.composetest.common.enums.Theme
import com.composetest.core.data.constants.preferencesdatastore.PreferencesDataKeys
import com.composetest.core.data.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.mappers.AppThemeModelMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class AppThemeRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource,
    private val appThemeModelMapper: AppThemeModelMapper,
) : AppThemeRepository {

    private val _customAppTheme = MutableStateFlow<Theme?>(null)
    override val customAppTheme = _customAppTheme.asStateFlow()

    override suspend fun setTheme(theme: Theme) {
        preferenceDataSource.setData(PreferencesDataKeys.appTheme, theme.name)
    }

    override suspend fun setDynamicColor(dynamicColor: Boolean) {
        preferenceDataSource.setData(PreferencesDataKeys.dynamicColor, dynamicColor)
    }

    override fun setCustomTheme(customTheme: Theme?) {
        _customAppTheme.update { customTheme }
    }

    override fun getAppTheme() = preferenceDataSource.getData { preferences ->
        val theme = preferences[PreferencesDataKeys.appTheme]
        val dynamicColors = preferences[PreferencesDataKeys.dynamicColor]
        appThemeModelMapper.invoke(theme, dynamicColors)
    }
}