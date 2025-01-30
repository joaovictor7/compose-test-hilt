package com.composetest.providers

import android.os.Build
import com.composetest.BuildConfig
import com.composetest.core.domain.enums.BuildType.Companion.getBuildType
import com.composetest.core.domain.enums.Flavor.Companion.getFlavor
import com.composetest.core.domain.models.BuildConfigModel
import com.composetest.core.domain.providers.BuildConfigProvider
import javax.inject.Inject

internal class BuildConfigProviderImpl @Inject constructor() : BuildConfigProvider {

    override val get = BuildConfigModel(
        applicationId = BuildConfig.APPLICATION_ID,
        versionName = BuildConfig.VERSION_NAME,
        versionCode = BuildConfig.VERSION_CODE,
        buildType = BuildConfig.BUILD_TYPE.getBuildType(),
        flavor = BuildConfig.FLAVOR.getFlavor(),
        androidSdkVersion = Build.VERSION.SDK_INT,
    )
}