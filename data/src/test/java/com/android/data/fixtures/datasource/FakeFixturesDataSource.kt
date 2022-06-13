package com.android.data.fixtures.datasource

import com.android.data.datasources.FixturesDataSource
import com.android.data.fixtures.entity.FixturesServerModel
import com.android.data.fixtures.entity.LiveFixturesServerModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeFixturesDataSource(
    private val fixturesServerModel: FixturesServerModel? = null,
    private val liveFixturesServerModel: LiveFixturesServerModel? = null
) : FixturesDataSource {

    override suspend fun getFixturesByDate(date: String): Response<FixturesServerModel> {
        return if (fixturesServerModel != null) {
            Response.success(fixturesServerModel)
        } else {
            Response.error(
                400,
                "{\"api\":[\"null\"]}".toResponseBody("application/json".toMediaTypeOrNull())
            )
        }
    }

    override suspend fun getLiveFixtures(): Response<LiveFixturesServerModel> {
        return if (liveFixturesServerModel != null) {
            Response.success(liveFixturesServerModel)
        } else {
            Response.error(
                400,
                "{\"api\":[\"null\"]}".toResponseBody("application/json".toMediaTypeOrNull())
            )
        }
    }
}