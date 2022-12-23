package com.anonymous.fixtures

import com.anonymous.ui.model.IState

data class FixturesState(
    val idle: Boolean = true,
    val isLoading: Boolean = false,
    val yesterdayFixturesState: DailyFixturesState = DailyFixturesState(),
    val todayFixturesState: DailyFixturesState = DailyFixturesState(),
    val tomorrowFixturesState: DailyFixturesState = DailyFixturesState(),
    val dayAfterTomorrowFixturesState: DailyFixturesState = DailyFixturesState(),
    val somedayDate: String = "",
    val somedayFixturesState: DailyFixturesState = DailyFixturesState(),
    val errorMessage: String? = null
) : IState