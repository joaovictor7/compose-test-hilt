package com.composetest.providers

import android.os.Build
import com.composetest.BuildConfig
import com.composetest.common.enums.BuildType.Companion.getBuildType
import com.composetest.common.enums.Flavor.Companion.getFlavor
import com.composetest.common.models.BuildConfigFieldsModel
import com.composetest.common.models.BuildConfigModel
import com.composetest.common.providers.BuildConfigProvider
import javax.inject.Inject

internal class BuildConfigProviderImpl @Inject constructor() : BuildConfigProvider {
    override val get = BuildConfigModel(
        applicationId = BuildConfig.APPLICATION_ID,
        versionName = BuildConfig.VERSION_NAME,
        versionCode = BuildConfig.VERSION_CODE,
        buildType = BuildConfig.BUILD_TYPE.getBuildType(),
        flavor = BuildConfig.FLAVOR.getFlavor(),
        androidSdkVersion = Build.VERSION.SDK_INT,
        buildConfigFieldsModel = BuildConfigFieldsModel(
            bffApiHost = BuildConfig.BFF_API_HOST,
            bffApiPort = BuildConfig.BFF_API_PORT,
            newsApiKey = BuildConfig.NEWS_API_API_KEY
        )
    )
}