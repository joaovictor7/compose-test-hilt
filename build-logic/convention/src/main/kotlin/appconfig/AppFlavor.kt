package appconfig

internal enum class AppFlavor {
    DEVELOP, STAGING, PRODUCTION, FULL, FREE;

    val isDefault get() = this == FULL

    override fun toString() = name.lowercase()
}