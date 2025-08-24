plugins {
    alias(libs.plugins.composetest.kotlin.library)
}

dependencies {
    implementation(libs.junit.jupiter)
    implementation(libs.kotlin.coroutines.test)
    implementation(libs.mockk)
}
