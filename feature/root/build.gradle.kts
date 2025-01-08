plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.test)
    alias(libs.plugins.composeTest.compose)
}

android {
    namespace = "com.composetest.feature.root"
}

dependencies {
    implementation(projects.core.router)
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.feature.home)
    implementation(projects.feature.configuration)
    implementation(projects.feature.profile)
    implementation(projects.feature.weatherforecast)
}