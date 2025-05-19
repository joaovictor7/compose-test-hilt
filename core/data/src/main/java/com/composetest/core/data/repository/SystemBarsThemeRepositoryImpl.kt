package com.composetest.core.data.repository

import com.composetest.core.data.datasource.local.StatusBarsThemeDataSource
import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.repository.SystemBarsThemeRepository
import javax.inject.Inject

internal class SystemBarsThemeRepositoryImpl @Inject constructor(
    private val statusBarsThemeDataSource: StatusBarsThemeDataSource,
) : SystemBarsThemeRepository {

    override fun getStatusBarsTheme() = statusBarsThemeDataSource.statusBarsTheme

    override fun setStatusBarsTheme(theme: Theme?) {
        statusBarsThemeDataSource.setStatusBarsTheme(theme)
    }
}