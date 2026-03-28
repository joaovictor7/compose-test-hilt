package modularization

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import enums.Signing
import org.gradle.api.Project
import enums.BuildType as BuildTypeEnum

internal fun Project.setAppBuildTypes(appExtension: ApplicationExtension) {
    appExtension.buildTypes {
        BuildTypeEnum.entries.forEach { buildType ->
            if (buildType.isInternal) {
                getByName(buildType.toString()) {
                    configAppBuildType(this@setAppBuildTypes, appExtension, buildType)
                }
            } else {
                create(buildType.toString()) {
                    configAppBuildType(this@setAppBuildTypes, appExtension, buildType)
                }
            }
        }
    }
}

internal fun Project.setLibraryBuildTypes(libraryExtension: LibraryExtension) {
    libraryExtension.buildTypes {
        BuildTypeEnum.entries.forEach { buildType ->
            if (buildType.isInternal) {
                getByName(buildType.toString()) {
                    isDefault = buildType.isDefault
                    if (buildType == BuildTypeEnum.RELEASE) {
                        isMinifyEnabled = false
                    }
                }
            } else {
                create(buildType.toString()) {
                    isDefault = buildType.isDefault
                    if (buildType == BuildTypeEnum.RELEASE) {
                        isMinifyEnabled = false
                    }
                }
            }
        }
    }
}

private fun ApplicationBuildType.configAppBuildType(
    project: Project,
    appExtension: ApplicationExtension,
    buildType: BuildTypeEnum
) {
    isDefault = buildType.isDefault
    isDebuggable = buildType.isDebuggable
    setSigning(appExtension, buildType)
    setBuildConfigFields(project, buildType)
    if (buildType == BuildTypeEnum.RELEASE) {
        isMinifyEnabled = false
    }
}

private fun ApplicationBuildType.setSigning(
    appExtension: ApplicationExtension,
    buildType: BuildTypeEnum
) {
    val signing = Signing.getAssociatedBuildType(buildType) ?: return
    val signingConfig = appExtension.signingConfigs.find { it.name == signing.toString() } ?: return
    this.signingConfig = signingConfig
}
