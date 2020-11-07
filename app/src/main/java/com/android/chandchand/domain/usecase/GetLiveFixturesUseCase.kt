package com.android.chandchand.domain.usecase

import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.entities.LiveFixtureEntities
import com.android.chandchand.domain.repositories.FixturesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLiveFixturesUseCase @Inject constructor(
    private val fixturesRepository: FixturesRepository
) {
    suspend fun execute(): Flow<Result<LiveFixtureEntities>> {
        return fixturesRepository.getLiveFixtures()
    }
}