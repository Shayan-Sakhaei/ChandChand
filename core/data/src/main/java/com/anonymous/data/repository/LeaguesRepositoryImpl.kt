package com.anonymous.data.repository

import com.anonymous.common.result.Result
import com.anonymous.data.mapper.StandingServerEntityMapper
import com.anonymous.data.model.StandingEntity
import com.anonymous.network.datasource.LeaguesDataSource
import com.anonymous.network.model.StandingsServerModel
import javax.inject.Inject

class LeaguesRepositoryImpl @Inject constructor(
    private val remoteDataSource: LeaguesDataSource,
    private val serverEntityMapper: StandingServerEntityMapper,
) : LeaguesRepository {

    override suspend fun getStandings(leagueId: Int): Result<List<StandingEntity>> {
        val response = remoteDataSource.getStandingsByLeagueId(leagueId)
        return response.fold(
            onSuccess = { model: StandingsServerModel ->
                val entities = model.api.standings[0].map { serverEntityMapper.map(it) }
                Result.Success(entities)
            },
            onFailure = { t: Throwable ->
                Result.Error(t.localizedMessage ?: "failed to get Response!")
            }
        )
    }
}