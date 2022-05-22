package com.android.chandchand.domain.usecase

import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.entities.FixtureEntity
import com.android.chandchand.domain.repositories.FixturesRepository
import javax.inject.Inject

class GetFixturesUseCase @Inject constructor(
    private val fixturesRepository: FixturesRepository
) {
    suspend fun execute(date: String): Result<List<FixtureEntity>> {
        return fixturesRepository.getFixtures(date)
    }
}