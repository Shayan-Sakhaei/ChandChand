package com.android.chandchand.presentation.leagues

import com.android.chandchand.presentation.common.IIntent

sealed class LeaguesIntent : IIntent {
    data class GetStandings(val leagueId: Int) : LeaguesIntent()
}