package com.android.chandchand.domain.datasources

import com.android.chandchand.data.fixtures.entity.FixturesServerModel
import retrofit2.Response

interface FixturesDataSource {
    suspend fun getFixturesByDate(date: String): Response<FixturesServerModel>
}