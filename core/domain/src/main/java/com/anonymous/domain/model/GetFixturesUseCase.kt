package com.anonymous.domain.model

import com.anonymous.common.result.Result
import com.anonymous.data.model.FixtureEntity
import com.anonymous.data.repository.FixturesRepository
import javax.inject.Inject

class GetFixturesUseCase @Inject constructor(
    private val fixturesRepository: FixturesRepository
) {
    suspend fun execute(date: String): Result<List<FixtureEntity>> {
        return fixturesRepository.getFixtures(date)
    }
}