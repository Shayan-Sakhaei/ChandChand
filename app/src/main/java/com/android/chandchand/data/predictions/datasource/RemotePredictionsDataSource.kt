package com.android.chandchand.data.predictions.datasource

import com.android.chandchand.data.predictions.api.PredictionsApi
import com.android.chandchand.data.predictions.entity.PredictionsServerModel
import com.android.chandchand.domain.datasources.PredictionsDataSource
import retrofit2.Response
import javax.inject.Inject

class RemotePredictionsDataSource @Inject constructor(
    private val api: PredictionsApi
) : PredictionsDataSource {
    override suspend fun getPredictions(fixtureId: Int): Response<PredictionsServerModel> =
        api.getPredictions(fixtureId)
}