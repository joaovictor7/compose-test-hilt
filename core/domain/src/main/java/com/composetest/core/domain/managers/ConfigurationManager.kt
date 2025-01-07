package com.composetest.core.domain.managers

import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.models.ConfigurationModel
import com.composetest.core.domain.models.ThemeModel
import kotlinx.coroutines.flow.Flow

interface ConfigurationManager {

    val currentConfiguration: ConfigurationModel?
    val theme: Flow<ThemeModel?>

    suspend fun fetchConfiguration()
    suspend fun getCurrentTheme(): ThemeModel
    suspend fun setTheme(theme: Theme)
    fun setStatusBarsTheme(theme: Theme?)
    suspend fun setDynamicColors(dynamicColor: Boolean)
    suspend fun setBiometricLogin(biometricLogin: Boolean)
}