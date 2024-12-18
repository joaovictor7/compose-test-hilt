package modularization

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.BaseExtension
import enums.BuildType
import enums.Signing
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.setBuildTypes(application: Boolean) = extensions.configure<BaseExtension> {
    BuildType.values().forEach { buildType ->
        if (buildType.isInternal) {
            setInternalBuildTypes(project, buildType, application)
        } else {
            createNewBuildTypes(project, buildType, application)
        }
    }
}

private fun BaseExtension.setInternalBuildTypes(
    project: Project,
    buildType: BuildType,
    application: Boolean
) = buildTypes {
    getByName(buildType.toString()) {
        isDefault = buildType.isDefault
        isDebuggable = buildType.isDebuggable
        if (application) {
            setSigning(this@setInternalBuildTypes, buildType)
            setBuildConfigFields(project, buildType)
        }
        if (buildType == BuildType.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

private fun BaseExtension.createNewBuildTypes(
    project: Project,
    buildType: BuildType,
    application: Boolean
) = buildTypes {
    create(buildType.toString()) {
        isDefault = buildType.isDefault
        isDebuggable = buildType.isDebuggable
        if (application) {
            setSigning(this@createNewBuildTypes, buildType)
            setBuildConfigFields(project, buildType)
        }
    }
}

private fun ApplicationBuildType.setSigning(
    baseExtension: BaseExtension,
    buildType: BuildType,
) = with(baseExtension) {
    Signing.getAssociatedBuildType(buildType)?.let { appSigning ->
        signingConfigs.find { signingConfig ->
            signingConfig.name == appSigning.signingName
        }?.let { signing -> signingConfig = signing }
    }
}
