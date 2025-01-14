package files

import enums.BuildType
import enums.Flavor
import org.gradle.api.Project
import java.util.Properties

internal class LoadPropertiesFile(
    private val project: Project,
    propertiesFile: PropertiesFile
) {
    private val fullPathProperties = getProperties(propertiesFile.fullPath)
    private val pathProperties = getProperties(propertiesFile.file)

    constructor(
        project: Project,
        flavor: Flavor
    ) : this(project, PropertiesFile.App(flavor.toString()))

    constructor(
        project: Project,
        buildType: BuildType
    ) : this(project, PropertiesFile.App(buildType.toString()))

    fun getProperty(key: String) = fullPathProperties?.getProperty(key)
        ?: pathProperties?.getProperty(key)
        ?: EMPTY_VALUE

    private fun getProperties(path: String): Properties? {
        val file = project.file("${project.rootDir}/app-properties/$path")
        return if (file.exists()) {
            Properties().apply { load(file.inputStream()) }
        } else null
    }

    private companion object {
        const val EMPTY_VALUE = "\"\""
    }
}