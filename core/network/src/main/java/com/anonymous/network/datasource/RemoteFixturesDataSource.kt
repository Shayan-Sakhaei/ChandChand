package com.anonymous.network.datasource

import com.anonymous.network.api.FixturesApi
import com.anonymous.network.model.FixturesServerModel
import com.anonymous.network.model.LiveFixturesServerModel
import retrofit2.Response
import javax.inject.Inject

class RemoteFixturesDataSource @Inject constructor(
    private val api: FixturesApi
) : FixturesDataSource {
    override suspend fun getFixturesByDate(date: String): Response<FixturesServerModel> =
        api.getAllFixturesByDate(date)

    override suspend fun getLiveFixtures(): Response<LiveFixturesServerModel> =
        api.getAllLiveFixtures()

}