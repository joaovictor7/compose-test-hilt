plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.test)
    alias(libs.plugins.composeTest.compose)
}

android {
    namespace = "com.composetest.feature.configuration"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.router)
}