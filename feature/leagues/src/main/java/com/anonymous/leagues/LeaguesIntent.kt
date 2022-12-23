package com.anonymous.leagues

import com.anonymous.ui.model.IIntent

sealed class LeaguesIntent : IIntent {
    data class GetStandings(val leagueId: Int) : LeaguesIntent()
}