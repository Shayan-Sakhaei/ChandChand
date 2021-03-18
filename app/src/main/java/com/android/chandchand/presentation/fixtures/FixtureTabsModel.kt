package com.android.chandchand.presentation.fixtures

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class FixtureTabsModel : Parcelable {
    Yesterday,
    Today,
    Tomorrow,
    DayAfterTomorrow;
}