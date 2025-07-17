plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.test.android"
}

dependencies {
    implementation(projects.core.test.kotlin)
    implementation(libs.junit5)
    implementation(libs.kotlin.coroutines.test)
    implementation(libs.mockk)
}