package com.android.chandchand.domain.usecase

import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.entities.StandingEntity
import com.android.chandchand.domain.repositories.LeaguesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStandingsUseCase @Inject constructor(
    private val leaguesRepository: LeaguesRepository
) {
    suspend fun execute(leagueId: Int): Flow<Result<List<StandingEntity>>> {
        return leaguesRepository.getStandings(leagueId)
    }
}