// TODO: Remove once https://youtrack.jetbrains.com/issue/KTIJ-19369 is fixed
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.ksp)
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "com.android.chandchand"
        minSdk = 21
        targetSdk = 33
        multiDexEnabled = true
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.android.chandchand.CustomTestRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ), "proguard-rules.pro"
            )
        }
        create("benchmark") {
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        val options = this
        options.jvmTarget = "1.8"
    }

    testOptions.unitTests.isIncludeAndroidResources = true
    testOptions.unitTests.isReturnDefaultValues = true

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))

    implementation(project(":feature:fixtures"))
    implementation(project(":feature:leagues"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Material
    implementation(libs.android.material)

    //MULTIDEX
    implementation(libs.androidx.multidex)

    //HILT
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)


    //PERFORMANCE
    implementation(libs.androidx.profileinstaller)
}