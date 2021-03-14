package com.android.chandchand.presentation.fixtures.live

import com.android.chandchand.presentation.common.IIntent

sealed class LiveFixturesIntent : IIntent {
    object GetLiveFixtures : LiveFixturesIntent()
}