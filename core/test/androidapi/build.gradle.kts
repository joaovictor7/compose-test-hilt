plugins {
    alias(libs.plugins.composetest.android.library)
}

android {
    namespace = "com.composetest.core.test.androidapi"
}

dependencies {
    implementation(projects.core.test.api)
    implementation(libs.junit.jupiter)
    implementation(libs.kotlin.coroutines.test)
    implementation(libs.mockk)
}