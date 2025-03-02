package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.StatusBarsThemeDataSource
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.repositories.SystemBarsThemeRepository
import javax.inject.Inject

internal class SystemBarsThemeRepositoryImpl @Inject constructor(
    private val statusBarsThemeDataSource: StatusBarsThemeDataSource,
) : SystemBarsThemeRepository {

    override fun getStatusBarsTheme() = statusBarsThemeDataSource.statusBarsTheme

    override fun setStatusBarsTheme(theme: Theme?) {
        statusBarsThemeDataSource.setStatusBarsTheme(theme)
    }
}