package file

import enums.Flavor
import enums.Signing
import java.io.File

internal sealed class PropertiesFile {
    abstract val path: String
    abstract val file: String
    val fullPath get() = "$path/$file"

    data class SigningKey(
        private val rootDir: File,
        private val signing: Signing
    ) : PropertiesFile() {
        override val file = "signing-key.properties"
        override val path = signing.toString()
        val keyPath = "$rootDir/signing-keys/$path/key"
    }

    data class App(private val flavor: Flavor) : PropertiesFile() {
        override val file = "app.properties"
        override val path = flavor.toString()
    }
}