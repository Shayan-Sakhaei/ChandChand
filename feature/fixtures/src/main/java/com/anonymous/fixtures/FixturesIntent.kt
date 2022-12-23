package com.anonymous.fixtures

import com.anonymous.ui.model.IIntent

sealed class FixturesIntent : IIntent {
    object GetFixtures : FixturesIntent()
    data class GetSomedayFixtures(val date: String) : FixturesIntent()
}