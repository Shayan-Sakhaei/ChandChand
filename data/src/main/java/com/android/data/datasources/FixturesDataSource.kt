package com.android.data.datasources

import com.android.data.fixtures.entity.FixturesServerModel
import com.android.data.fixtures.entity.LiveFixturesServerModel
import retrofit2.Response

interface FixturesDataSource {
    suspend fun getFixturesByDate(date: String): Response<FixturesServerModel>
    suspend fun getLiveFixtures(): Response<LiveFixturesServerModel>
}