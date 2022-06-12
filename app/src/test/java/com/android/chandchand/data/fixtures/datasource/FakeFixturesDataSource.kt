package com.android.chandchand.data.fixtures.datasource

import com.android.data.fixtures.entity.FixturesServerModel
import com.android.data.fixtures.entity.LiveFixturesServerModel
import com.android.data.datasources.FixturesDataSource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeFixturesDataSource(
    private val fixturesServerModel: com.android.data.fixtures.entity.FixturesServerModel? = null,
    private val liveFixturesServerModel: com.android.data.fixtures.entity.LiveFixturesServerModel? = null
) : com.android.data.datasources.FixturesDataSource {

    override suspend fun getFixturesByDate(date: String): Response<com.android.data.fixtures.entity.FixturesServerModel> {
        return if (fixturesServerModel != null) {
            Response.success(fixturesServerModel)
        } else {
            Response.error(
                400,
                "{\"api\":[\"null\"]}".toResponseBody("application/json".toMediaTypeOrNull())
            )
        }
    }

    override suspend fun getLiveFixtures(): Response<com.android.data.fixtures.entity.LiveFixturesServerModel> {
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