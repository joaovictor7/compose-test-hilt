package modularization

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BuildType
import enum.Signing
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import enum.BuildType as BuildTypeEnum

internal fun Project.setBuildTypes(isApplication: Boolean) = extensions.configure<BaseExtension> {
    buildTypes {
        BuildTypeEnum.values().forEach { buildType ->
            if (buildType.isInternal) {
                getByName(buildType.toString()) {
                    configBuildType(this@setBuildTypes, this@configure, buildType, isApplication)
                }
            } else {
                create(buildType.toString()) {
                    configBuildType(this@setBuildTypes, this@configure, buildType, isApplication)
                }
            }
        }
    }
}

private fun BuildType.configBuildType(
    project: Project,
    baseExtension: BaseExtension,
    buildType: BuildTypeEnum,
    isApplication: Boolean
) = with(baseExtension) {
    isDefault = buildType.isDefault
    isDebuggable = buildType.isDebuggable
    if (isApplication) {
        setSigning(this, buildType)
        setBuildConfigFields(project, buildType)
    }
    if (buildType == BuildTypeEnum.RELEASE) {
        isMinifyEnabled = false
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
    }
}

private fun ApplicationBuildType.setSigning(
    baseExtension: BaseExtension,
    buildType: BuildTypeEnum
) = with(baseExtension) {
    val signing = Signing.getAssociatedBuildType(buildType) ?: return@with
    val signingConfig = signingConfigs.find { it.name == signing.toString() } ?: return@with
    this@setSigning.signingConfig = signingConfig
}