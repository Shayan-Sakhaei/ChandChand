package com.android.domain.usecase

import com.android.domain.common.Result
import com.android.domain.entities.LiveFixtureEntities
import com.android.domain.repositories.FixturesRepository
import javax.inject.Inject

class GetLiveFixturesUseCase @Inject constructor(
    private val fixturesRepository: FixturesRepository
) {
    suspend fun execute(): Result<LiveFixtureEntities> {
        return fixturesRepository.getLiveFixtures()
    }
}