package com.android.chandchand.data.leagues.repository

import com.android.chandchand.data.common.Result
import com.android.chandchand.data.leagues.mapper.StandingServerEntityMapper
import com.android.chandchand.domain.datasources.LeaguesDataSource
import com.android.domain.entities.StandingEntity
import com.android.domain.repositories.LeaguesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LeaguesRepositoryImpl @Inject constructor(
    private val remoteDataSource: LeaguesDataSource,
    private val serverEntityMapper: StandingServerEntityMapper,
) : com.android.domain.repositories.LeaguesRepository {

    override suspend fun getStandings(leagueId: Int): Flow<Result<List<com.android.domain.entities.StandingEntity>>> {
        return flow {
            val response = remoteDataSource.getStandingsByLeagueId(leagueId)
            val body = response.body()
            if (!response.isSuccessful || body == null) {
                emit(Result.Error(""))
            } else {
                val entities = body.api.standings[0].map {
                    serverEntityMapper.map(it)
                }
                emit(Result.Success(entities))
            }
        }.flowOn(Dispatchers.IO)
    }
}