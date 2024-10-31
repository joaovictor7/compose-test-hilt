package appconfig

internal enum class AppFlavorDimension(val flavors: List<AppFlavor>) {
    ENVIRONMENT(listOf(AppFlavor.DEVELOP, AppFlavor.STAGING, AppFlavor.PRODUCTION)),
    DISTRIBUTION(listOf(AppFlavor.FULL, AppFlavor.FREE));

    override fun toString() = name.lowercase()

    companion object {
        val allDimensions get() = values().map { it.toString() }
    }
}