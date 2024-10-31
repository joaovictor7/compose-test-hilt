package appconfig

internal enum class AppBuildType {
    RELEASE,
    DEBUG;

    val isDefault get() = this == DEBUG
    val isInternal get() = this in listOf(RELEASE, DEBUG)
    val isDebuggable get() = this in listOf(DEBUG)

    override fun toString() = name.lowercase()
}