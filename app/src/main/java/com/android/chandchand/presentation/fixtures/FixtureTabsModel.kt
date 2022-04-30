package com.android.chandchand.presentation.fixtures

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class FixtureTabsModel : Parcelable {
    Yesterday,
    Today,
    Tomorrow,
    DayAfterTomorrow;
}