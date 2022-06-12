package com.android.domain.usecase

import com.android.domain.entities.FixtureEntity
import com.android.domain.repositories.FixturesRepository
import javax.inject.Inject

class GetFixturesUseCase @Inject constructor(
    private val fixturesRepository: FixturesRepository
) {
    suspend fun execute(date: String): Result<List<FixtureEntity>> {
        return fixturesRepository.getFixtures(date)
    }
}