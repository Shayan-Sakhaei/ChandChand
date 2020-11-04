package com.android.chandchand.presentation.fixtures

import com.android.chandchand.presentation.common.IIntent
import com.android.chandchand.presentation.utils.WeekDay

sealed class FixturesIntent : IIntent {
    data class GetFixtures(val date: String, val weekDay: WeekDay) : FixturesIntent()
}