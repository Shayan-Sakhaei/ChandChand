package com.android.data.leagues.repository

import com.android.data.datasources.LeaguesDataSource
import com.android.data.leagues.mapper.StandingServerEntityMapper
import com.android.domain.common.Result
import com.android.domain.entities.StandingEntity
import com.android.domain.repositories.LeaguesRepository
import javax.inject.Inject

class LeaguesRepositoryImpl @Inject constructor(
    private val remoteDataSource: LeaguesDataSource,
    private val serverEntityMapper: StandingServerEntityMapper,
) : LeaguesRepository {

    override suspend fun getStandings(leagueId: Int): Result<List<StandingEntity>> {
        val response = remoteDataSource.getStandingsByLeagueId(leagueId)
        val body = response.body()
        return if (!response.isSuccessful || body == null) {
            Result.Error("")
        } else {
            val entities = body.api.standings[0].map {
                serverEntityMapper.map(it)
            }
            Result.Success(entities)
        }
    }
}