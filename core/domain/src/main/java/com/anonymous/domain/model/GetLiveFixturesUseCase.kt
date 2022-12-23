package com.anonymous.domain.model

import com.anonymous.common.result.Result
import com.anonymous.data.model.LiveFixtureEntities
import com.anonymous.data.repository.FixturesRepository
import javax.inject.Inject

class GetLiveFixturesUseCase @Inject constructor(
    private val fixturesRepository: FixturesRepository
) {
    suspend fun execute(): Result<LiveFixtureEntities> {
        return fixturesRepository.getLiveFixtures()
    }
}