package com.android.chandchand.presentation.utils

sealed class WeekDay {
    object Yesterday : WeekDay()
    object Today : WeekDay()
    object Tomorrow : WeekDay()
    object DayAfterTomorrow : WeekDay()
    object Someday : WeekDay()
}