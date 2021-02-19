package com.android.chandchand.data.fixtures.datasource

import com.android.chandchand.data.fixtures.api.FixturesApi
import com.android.chandchand.data.fixtures.entity.FixturesServerModel
import com.android.chandchand.data.fixtures.entity.LiveFixturesServerModel
import com.android.chandchand.domain.datasources.FixturesDataSource
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