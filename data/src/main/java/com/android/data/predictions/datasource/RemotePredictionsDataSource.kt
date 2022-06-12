package com.android.data.predictions.datasource

import com.android.data.datasources.PredictionsDataSource
import com.android.data.predictions.api.PredictionsApi
import com.android.data.predictions.entity.PredictionsServerModel
import retrofit2.Response
import javax.inject.Inject

class RemotePredictionsDataSource @Inject constructor(
    private val api: PredictionsApi
) : PredictionsDataSource {
    override suspend fun getPredictions(fixtureId: Int): Response<PredictionsServerModel> =
        api.getPredictions(fixtureId)
}