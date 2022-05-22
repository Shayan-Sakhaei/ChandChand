package com.android.chandchand.presentation.fixtures

import com.android.chandchand.presentation.common.IIntent

sealed class FixturesIntent : IIntent {
    object GetFixtures : FixturesIntent()
    data class GetSomedayFixtures(val date: String) : FixturesIntent()
}