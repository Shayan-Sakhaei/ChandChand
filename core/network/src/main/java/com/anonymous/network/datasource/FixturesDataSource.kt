package com.anonymous.network.datasource

import com.anonymous.network.model.FixturesServerModel
import com.anonymous.network.model.LiveFixturesServerModel

interface FixturesDataSource {
    suspend fun getFixturesByDate(date: String): Result<FixturesServerModel>
    suspend fun getLiveFixtures(): Result<LiveFixturesServerModel>
}