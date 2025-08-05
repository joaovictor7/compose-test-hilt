plugins {
    alias(libs.plugins.composetest.library)
    alias(libs.plugins.composetest.compose)
    alias(libs.plugins.composetest.android.test)
}

android {
    namespace = "com.composetest.feature.login"
}

dependencies {
    implementation(projects.common.api)
    implementation(projects.core.router)
    implementation(projects.core.network)
    implementation(projects.core.database.androidapi)
    implementation(projects.core.data.api)
    implementation(projects.core.data.androidapi)
    implementation(projects.core.domain)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.core.security.androidapi)
    implementation(projects.core.security.api)
    implementation(projects.core.analytic.api)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.work.manager)
}