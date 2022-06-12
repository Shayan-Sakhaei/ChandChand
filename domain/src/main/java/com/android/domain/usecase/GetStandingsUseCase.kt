package com.android.domain.usecase

import com.android.domain.entities.StandingEntity
import com.android.domain.repositories.LeaguesRepository
import javax.inject.Inject

class GetStandingsUseCase @Inject constructor(
    private val leaguesRepository: LeaguesRepository
) {
    suspend fun execute(leagueId: Int): Result<List<StandingEntity>> {
        return leaguesRepository.getStandings(leagueId)
    }
}