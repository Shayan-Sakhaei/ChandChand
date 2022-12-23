package com.anonymous.data.repository

import com.anonymous.common.result.Result
import com.anonymous.data.mapper.StandingServerEntityMapper
import com.anonymous.data.model.StandingEntity
import com.anonymous.network.datasource.LeaguesDataSource
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