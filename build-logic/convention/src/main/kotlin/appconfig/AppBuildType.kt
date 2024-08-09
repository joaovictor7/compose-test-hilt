package appconfig

internal enum class AppBuildType(val buildTypeName: String) {
    RELEASE("release"),
    DEBUG("debug"),
    STAGING("staging");

    val isDefault get() = this == DEBUG
    val isInternal get() = this in listOf(RELEASE, DEBUG)
    val isDebuggable get() = this in listOf(DEBUG, STAGING)
    val buildTypeNameUpperCase get() = buildTypeName.uppercase()
    val applicationIdSuffix
        get() = when (this) {
            DEBUG -> DEV_APP_ID_SUFFIX
            else -> buildTypeName
        }

    private companion object {
        const val DEV_APP_ID_SUFFIX = "dev"
    }
}