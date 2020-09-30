package com.android.chandchand.domain.repositories

import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.entities.StandingEntity
import kotlinx.coroutines.flow.Flow

interface LeaguesRepository {
    suspend fun getStandings(leagueId: Int): Flow<Result<List<StandingEntity>>>
}