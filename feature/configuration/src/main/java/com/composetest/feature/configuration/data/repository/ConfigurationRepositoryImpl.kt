package com.composetest.feature.configuration.data.repository

import com.composetest.core.data.datasource.PreferenceDataSource
import com.composetest.core.data.datastore.PreferencesDataKeys
import com.composetest.core.domain.model.ThemeConfigurationModel
import com.composetest.core.domain.repository.ConfigurationRepository
import com.composetest.feature.configuration.data.datasource.ConfigurationDataSource
import com.composetest.feature.configuration.data.mapper.ConfigurationMapper
import com.composetest.feature.configuration.data.mapper.SecurityConfigurationMapper
import com.composetest.feature.configuration.data.mapper.ThemeConfigurationMapper
import com.composetest.feature.configuration.domain.model.ConfigurationModel
import com.composetest.feature.configuration.domain.model.SecurityConfigurationModel
import javax.inject.Inject

internal class ConfigurationRepositoryImpl @Inject constructor(
    private val configurationDataSource: ConfigurationDataSource,
    private val preferenceDataSource: PreferenceDataSource,
    private val themeConfigurationMapper: ThemeConfigurationMapper,
    private val securityConfigurationMapper: SecurityConfigurationMapper,
    private val configurationMapper: ConfigurationMapper,
) : ConfigurationRepository {

    override suspend fun insertDefaultUserConfiguration(userId: String) {
        configurationDataSource.upsert(
            configurationMapper.mapperToEntity(ConfigurationModel(userId = userId))
        )
    }

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