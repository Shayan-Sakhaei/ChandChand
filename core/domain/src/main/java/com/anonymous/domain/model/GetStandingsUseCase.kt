package com.anonymous.domain.model

import com.anonymous.common.result.Result
import com.anonymous.data.model.StandingEntity
import com.anonymous.data.repository.LeaguesRepository
import javax.inject.Inject

class GetStandingsUseCase @Inject constructor(
    private val leaguesRepository: LeaguesRepository
) {
    suspend fun execute(leagueId: Int): Result<List<StandingEntity>> {
        return leaguesRepository.getStandings(leagueId)
    }
}