package files

import enums.BuildType
import enums.Flavor
import org.gradle.api.Project
import java.util.Properties

internal class LoadPropertiesFile(
    private val project: Project,
    private val propertiesFile: PropertiesFile
) {

    constructor(
        project: Project,
        flavor: Flavor
    ) : this(project, PropertiesFile.App(flavor.toString()))

    constructor(
        project: Project,
        buildType: BuildType
    ) : this(project, PropertiesFile.App(buildType.toString()))

    private val rootPropertiesDir = "${project.rootDir}/app-properties"

    fun getProperty(key: String): String {
        var property = getPropertyIfExists("$rootPropertiesDir/${propertiesFile.fullyPath}", key)
        if (property != null) return property
        property = getPropertyIfExists("$rootPropertiesDir/${propertiesFile.file}", key)
        return property ?: EMPTY_VALUE
    }

    private fun getPropertyIfExists(path: String, key: String): String? {
        val file = project.file(path)
        val properties = if (file.exists()) {
            Properties().apply { load(file.inputStream()) }
        } else null
        return properties?.getProperty(key)
    }

    private companion object {
        const val EMPTY_VALUE = "\"\""
    }
}