package com.anonymous.network.datasource

import com.anonymous.network.model.FixturesServerModel
import com.anonymous.network.model.LiveFixturesServerModel
import retrofit2.Response

interface FixturesDataSource {
    suspend fun getFixturesByDate(date: String): Response<FixturesServerModel>
    suspend fun getLiveFixtures(): Response<LiveFixturesServerModel>
}