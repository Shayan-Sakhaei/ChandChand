package com.android.chandchand.domain.repositories

import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.entities.FixtureEntity
import com.android.chandchand.domain.entities.LiveFixtureEntities
import kotlinx.coroutines.flow.Flow

interface FixturesRepository {
    fun getFixtures(date: String): Flow<Result<List<FixtureEntity>>>
    fun getLiveFixtures(): Flow<Result<LiveFixtureEntities>>
}