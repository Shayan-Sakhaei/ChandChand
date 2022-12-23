package com.anonymous.data.repository

import com.anonymous.common.result.Result
import com.anonymous.data.model.StandingEntity

interface LeaguesRepository {
    suspend fun getStandings(leagueId: Int): Result<List<StandingEntity>>
}