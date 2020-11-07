package com.android.chandchand.domain.datasources

import com.android.chandchand.data.fixtures.entity.FixturesServerModel
import com.android.chandchand.data.fixtures.entity.LiveFixturesServerModel
import retrofit2.Response

interface FixturesDataSource {
    suspend fun getFixturesByDate(date: String): Response<FixturesServerModel>
    suspend fun getLiveFixtures(): Response<LiveFixturesServerModel>
}