package com.android.chandchand.presentation.fixtures.livefixtures

import com.android.chandchand.presentation.common.IIntent

sealed class LiveFixturesIntent : IIntent {
    object GetLiveFixtures : LiveFixturesIntent()
}