package enums

internal enum class Signing(
    val buildTypes: List<BuildType>
) {
    RELEASE(listOf(BuildType.RELEASE));

    override fun toString() = name.lowercase()

    companion object {
        fun getAssociatedBuildType(buildType: BuildType) = values().find {
            buildType in it.buildTypes
        }
    }
}