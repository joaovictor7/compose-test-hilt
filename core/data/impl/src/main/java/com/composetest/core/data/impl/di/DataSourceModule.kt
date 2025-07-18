package com.composetest.core.data.impl.di

import com.composetest.core.data.androidapi.datasource.PreferenceDataSource
import com.composetest.core.data.impl.datasource.PreferenceDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {

    @Provides
    fun preferencesDataSource(
        preferenceDataSourceImpl: PreferenceDataSourceImpl
    ): PreferenceDataSource = preferenceDataSourceImpl
}