package extension

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal fun Project.getLibrary(id: String) = libs.findLibrary(id).get()

internal fun Project.findVersion(id: String) = libs.findVersion(id).get().toString()

private val Project.libs get() = extensions.getByType<VersionCatalogsExtension>().named("libs")