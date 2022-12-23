package com.anonymous.fixtures.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LeagueHeaderModel(
    @DrawableRes val leagueLogo: Int,
    @StringRes val leagueTitle: Int,
    val fixturesCount: Int
)