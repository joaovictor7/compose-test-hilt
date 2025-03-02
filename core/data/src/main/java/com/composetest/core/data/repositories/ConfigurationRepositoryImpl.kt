package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.ConfigurationDataSource
import com.composetest.core.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.mappers.SecurityConfigurationMapper
import com.composetest.core.data.mappers.ThemeConfigurationMapper
import com.composetest.core.data.preferencesdatastore.PreferencesDataKeys
import com.composetest.core.domain.models.configuration.SecurityConfigurationModel
import com.composetest.core.domain.models.configuration.ThemeConfigurationModel
import com.composetest.core.domain.repositories.ConfigurationRepository
import javax.inject.Inject

internal class ConfigurationRepositoryImpl @Inject constructor(
    private val configurationDataSource: ConfigurationDataSource,
    private val preferenceDataSource: PreferenceDataSource,
    private val securityConfigurationMapper: SecurityConfigurationMapper,
    private val themeConfigurationMapper: ThemeConfigurationMapper,
) : ConfigurationRepository {

    override suspend fun getSecurityConfiguration(): SecurityConfigurationModel? {
        val entity = configurationDataSource.getConfiguration()
        return securityConfigurationMapper.mapperToModel(entity)
    }

    override suspend fun getLastSecurityConfiguration(): SecurityConfigurationModel? {
        val entity = configurationDataSource.getLastConfiguration()
        return securityConfigurationMapper.mapperToModel(entity)
    }

    override fun getThemeConfiguration() = preferenceDataSource.getData { preferences ->
        val theme = preferences[PreferencesDataKeys.theme]
        val dynamicColor = preferences[PreferencesDataKeys.dynamicColor]
        themeConfigurationMapper.mapperToModel(theme, dynamicColor)
    }

    override suspend fun updateSecurityConfiguration(
        securityConfigurationModel: SecurityConfigurationModel,
    ) {
        val update = securityConfigurationMapper.mapperToUpdate(securityConfigurationModel)
        configurationDataSource.updateSecurityConfiguration(update)
    }

    override suspend fun updateThemeConfiguration(themeConfigurationModel: ThemeConfigurationModel) {
        preferenceDataSource.setData(PreferencesDataKeys.theme, themeConfigurationModel.theme.name)
        preferenceDataSource.setData(
            PreferencesDataKeys.dynamicColor,
            themeConfigurationModel.dynamicColor
        )
    }
}