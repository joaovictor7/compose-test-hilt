package appconfig

internal enum class AppFlavor(val flavorName: String) {
    FULL("full"),
    FREE("free");

    val isDefault get() = this == FULL
    val dimension
        get() = Dimension.values().find { dimension ->
            dimension.flavors.any { it == this }
        }?.toString() ?: DEFAULT_DIMENSION

    companion object {
        private const val DEFAULT_DIMENSION = "default"
        val allDimensions get() = Dimension.values().map { it.toString() }
    }
}

private enum class Dimension(val flavors: List<AppFlavor>) {
    DISTRIBUTION(listOf(AppFlavor.FULL, AppFlavor.FREE));

    override fun toString() = name.lowercase()
}