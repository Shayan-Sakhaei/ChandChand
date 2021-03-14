package com.android.chandchand.presentation.fixtures

import com.android.chandchand.presentation.common.IIntent

sealed class FixturesIntent : IIntent {
    data class GetFixtures(val date: String) : FixturesIntent()
}