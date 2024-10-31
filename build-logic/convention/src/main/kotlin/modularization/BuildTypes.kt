package modularization

import appconfig.AppBuildType
import appconfig.AppSigning
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.setBuildTypes(application: Boolean) = extensions.configure<BaseExtension> {
    AppBuildType.values().forEach { buildType ->
        if (buildType.isInternal) {
            setInternalBuildTypes(buildType, application)
        } else {
            createNewBuildTypes(buildType, application)
        }
    }
}

private fun BaseExtension.setInternalBuildTypes(
    buildType: AppBuildType,
    application: Boolean
) = buildTypes {
    getByName(buildType.toString()) {
        isDefault = buildType.isDefault
        isDebuggable = buildType.isDebuggable
        if (application) {
            setSigning(buildType, this@setInternalBuildTypes)
            setBuildConfigFields(buildType)
        }
        if (buildType == AppBuildType.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

private fun BaseExtension.createNewBuildTypes(
    buildType: AppBuildType,
    application: Boolean
) = buildTypes {
    create(buildType.toString()) {
        isDefault = buildType.isDefault
        isDebuggable = buildType.isDebuggable
        if (application) {
            setSigning(buildType, this@createNewBuildTypes)
            setBuildConfigFields(buildType)
        }
    }
}

private fun ApplicationBuildType.setSigning(
    buildType: AppBuildType,
    baseExtension: BaseExtension
) = with(baseExtension) {
    AppSigning.getAssociatedBuildType(buildType)?.let { appSigning ->
        signingConfigs.find { signingConfig ->
            signingConfig.name == appSigning.signingName
        }?.let { signing -> signingConfig = signing }
    }
}
