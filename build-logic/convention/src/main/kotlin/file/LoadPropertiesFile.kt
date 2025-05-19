package file

import enum.Flavor
import org.gradle.api.Project
import java.util.Properties

internal class LoadPropertiesFile(
    private val project: Project,
    private val propertiesFile: PropertiesFile
) {

    constructor(project: Project, flavor: Flavor) : this(project, PropertiesFile.App(flavor))

    fun getProperty(key: String) = getProperties(propertiesFile.fullPath)?.getProperty(key)
        ?: getProperties(propertiesFile.file)?.getProperty(key)
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