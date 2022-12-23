package com.anonymous.fixtures

import com.anonymous.ui.model.IIntent

sealed class LiveFixturesIntent : IIntent {
    object GetLiveFixtures : LiveFixturesIntent()
}