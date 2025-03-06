plugins {
    alias(libs.plugins.composeTest.library)

}

android {
    namespace = "com.composetest.core.test"
}

dependencies {
    implementation(libs.junit5)
    implementation(libs.kotlin.coroutines.test)
    implementation(libs.mockk)
}