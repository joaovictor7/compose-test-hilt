package com.composetest.feature.configuration.data.repositories

import com.composetest.core.data.datasources.PreferenceDataSource
import com.composetest.core.data.preferencesdatastore.PreferencesDataKeys
import com.composetest.feature.configuration.data.mappers.SecurityConfigurationMapper
import com.composetest.feature.configuration.data.datasources.local.ConfigurationDataSource
import com.composetest.feature.configuration.domain.models.SecurityConfigurationModel
import com.composetest.core.domain.models.configuration.ThemeConfigurationModel
import com.composetest.core.domain.repositories.ConfigurationRepository
import com.composetest.feature.configuration.data.mappers.ThemeConfigurationMapper
import javax.inject.Inject

internal class ConfigurationRepositoryImpl @Inject constructor(
    private val configurationDataSource: ConfigurationDataSource,
    private val preferenceDataSource: PreferenceDataSource,
    private val themeConfigurationMapper: ThemeConfigurationMapper,
    private val securityConfigurationMapper: SecurityConfigurationMapper,
) : ConfigurationRepository {

    override suspend fun getLastBiometricConfiguration() =
        configurationDataSource.getLastBiometricConfiguration()

    override fun getThemeConfiguration() = preferenceDataSource.getData { preferences ->
        val theme = preferences[PreferencesDataKeys.Configuration.theme]
        val dynamicColor = preferences[PreferencesDataKeys.Configuration.dynamicColor]
        themeConfigurationMapper.mapperToModel(theme, dynamicColor)
    }

    suspend fun getSecurityConfiguration(): SecurityConfigurationModel? {
        val entity = configurationDataSource.getConfiguration()
        return securityConfigurationMapper.mapperToModel(entity)
    }

    suspend fun updateSecurityConfiguration(securityConfigurationModel: SecurityConfigurationModel) {
        val update = securityConfigurationMapper.mapperToUpdate(securityConfigurationModel)
        configurationDataSource.updateSecurityConfiguration(update)
    }

    suspend fun updateThemeConfiguration(themeConfigurationModel: ThemeConfigurationModel) {
        preferenceDataSource.setData(
            PreferencesDataKeys.Configuration.theme,
            themeConfigurationModel.theme.name
        )
        preferenceDataSource.setData(
            PreferencesDataKeys.Configuration.dynamicColor,
            themeConfigurationModel.dynamicColor
        )
    }
}