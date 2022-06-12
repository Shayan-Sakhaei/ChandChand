package com.android.data.fixtures.datasource

import com.android.data.fixtures.api.FixturesApi
import com.android.data.fixtures.entity.FixturesServerModel
import com.android.data.fixtures.entity.LiveFixturesServerModel
import retrofit2.Response
import javax.inject.Inject

class RemoteFixturesDataSource @Inject constructor(
    private val api: FixturesApi
) : com.android.data.datasources.FixturesDataSource {
    override suspend fun getFixturesByDate(date: String): Response<FixturesServerModel> =
        api.getAllFixturesByDate(date)

    override suspend fun getLiveFixtures(): Response<LiveFixturesServerModel> =
        api.getAllLiveFixtures()

}