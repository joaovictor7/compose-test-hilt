plugins {
    alias(libs.plugins.composeTest.kotlin)
}

dependencies {
    implementation(libs.junit5)
    implementation(libs.kotlin.coroutines.test)
    implementation(libs.mockk)
}
