package com.android.domain.repositories

import com.android.domain.entities.StandingEntity

interface LeaguesRepository {
    suspend fun getStandings(leagueId: Int): Result<List<StandingEntity>>
}