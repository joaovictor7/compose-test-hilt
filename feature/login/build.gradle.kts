plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
    alias(libs.plugins.composeTest.androidTest)
}

android {
    namespace = "com.composetest.feature.login"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.router)
    implementation(projects.core.network)
    implementation(projects.core.database)
    implementation(projects.core.data.api)
    implementation(projects.core.data.androidapi)
    implementation(projects.core.domain)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.core.security)
    implementation(projects.core.analytic.api)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.work.manager)
}