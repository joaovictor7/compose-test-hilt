package modularization

import appconfig.AppBuildType
import appconfig.AppConfig
import appconfig.AppSigning
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun ApplicationExtension.setApplicationBuildTypes() {
    buildTypes {
        AppBuildType.values().forEach { buildType ->
            getByName(buildType.buildTypeName) {
                setSigning(buildType, this@setApplicationBuildTypes)
                setApplicationIdSuffix(buildType)
                setManifestPlaceholders(buildType)
                setBuildConfigFields(buildType)
            }
        }
    }
}

internal fun Project.setBuildTypesAllModules() = extensions.configure<BaseExtension> {
    AppBuildType.values().forEach { buildType ->
        if (buildType.isInternal) {
            setInternalBuildTypes(buildType)
        } else {
            createNewBuildTypes(buildType)
        }
    }
}

private fun BaseExtension.setInternalBuildTypes(buildType: AppBuildType) = buildTypes {
    getByName(buildType.buildTypeName) {
        isDefault = buildType.isDefault
        isDebuggable = buildType.isDebuggable
        when (buildType) {
            AppBuildType.RELEASE -> {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
            else -> Unit
        }
    }
}

private fun BaseExtension.createNewBuildTypes(buildType: AppBuildType) = buildTypes {
    create(buildType.buildTypeName) {
        isDefault = buildType.isDefault
        isDebuggable = buildType.isDebuggable
    }
}

private fun ApplicationBuildType.setSigning(
    buildType: AppBuildType,
    applicationExtension: ApplicationExtension
) = with(applicationExtension) {
    AppSigning.getAssociatedBuildType(buildType)?.let { appSigning ->
        signingConfigs.find { signingConfig ->
            signingConfig.name == appSigning.signingName
        }?.let { signing -> signingConfig = signing }
    }
}

private fun ApplicationBuildType.setApplicationIdSuffix(buildType: AppBuildType) {
    if (buildType != AppBuildType.RELEASE) {
        versionNameSuffix = "-${buildType.buildTypeNameUpperCase}"
        applicationIdSuffix = ".${buildType.applicationIdSuffix}"
    }
}

private fun ApplicationBuildType.setManifestPlaceholders(buildType: AppBuildType) {
    var appName = "${AppConfig.APP_NAME} - ${buildType.buildTypeNameUpperCase}"
    var usesClearTextTraffic = true
    if (buildType == AppBuildType.RELEASE) {
        appName = AppConfig.APP_NAME
        usesClearTextTraffic = false
    }
    manifestPlaceholders["appName"] = appName
    manifestPlaceholders["usesCleartextTraffic"] = usesClearTextTraffic
}
