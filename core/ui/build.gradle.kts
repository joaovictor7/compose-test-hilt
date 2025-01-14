plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
}

android {
    namespace = "com.composetest.core.ui"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.common)
    implementation(projects.core.router)
    implementation(projects.core.designsystem)
}