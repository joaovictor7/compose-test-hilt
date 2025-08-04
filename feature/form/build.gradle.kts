plugins {
    alias(libs.plugins.composetest.library)
    alias(libs.plugins.composetest.compose)
    alias(libs.plugins.composetest.test)
}

android {
    namespace = "com.composetest.feature.form"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.network)
    implementation(projects.core.router)
    implementation(projects.core.designsystem)
    implementation(projects.core.data.api)
    implementation(projects.core.domain)
    implementation(projects.core.database.androidapi)
    implementation(projects.core.ui)
    implementation(projects.core.analytic.api)
}