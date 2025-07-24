plugins {
    alias(libs.plugins.composetest.library)
    alias(libs.plugins.composetest.compose)
    alias(libs.plugins.composetest.android.test)
}

android {
    namespace = "com.composetest.feature.profile"
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.core.database)
    implementation(projects.core.data.api)
    implementation(projects.core.domain)
    implementation(projects.core.router)
    implementation(projects.core.analytic.api)
    implementation(projects.core.security.api)
}