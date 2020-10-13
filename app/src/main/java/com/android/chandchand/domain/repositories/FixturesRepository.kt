package com.android.chandchand.domain.repositories

import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.entities.FixtureEntity
import kotlinx.coroutines.flow.Flow

interface FixturesRepository {
    suspend fun getFixtures(date: String): Flow<Result<List<FixtureEntity>>>
}