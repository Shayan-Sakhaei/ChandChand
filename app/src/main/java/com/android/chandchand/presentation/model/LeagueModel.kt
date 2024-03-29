package com.android.chandchand.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LeagueModel(
    @DrawableRes val leagueLogo: Int,
    @StringRes val leagueTitle: Int,
    val fixturesCount: Int
)