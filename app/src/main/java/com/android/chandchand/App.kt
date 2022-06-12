package com.android.chandchand

import androidx.multidex.MultiDexApplication
import com.android.data.common.PreferenceRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : MultiDexApplication() {

    @Inject
    lateinit var preferenceRepository: com.android.data.common.PreferenceRepository
}