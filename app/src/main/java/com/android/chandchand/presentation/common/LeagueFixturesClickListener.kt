package com.android.chandchand.presentation.common

import com.android.chandchand.presentation.model.LeagueModel

interface LeagueFixturesClickListener {
    fun onHeaderClicked(leagueModel: LeagueModel)
    fun onPredictionClicked(fixtureId: Int, homeTeamLogo: String?, awayTeamLogo: String?)
}