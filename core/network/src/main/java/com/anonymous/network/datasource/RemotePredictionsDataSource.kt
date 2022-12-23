package com.anonymous.network.datasource

import com.anonymous.network.api.PredictionsApi
import com.anonymous.network.model.PredictionsServerModel
import retrofit2.Response
import javax.inject.Inject

class RemotePredictionsDataSource @Inject constructor(
    private val api: PredictionsApi
) : PredictionsDataSource {
    override suspend fun getPredictions(fixtureId: Int): Response<PredictionsServerModel> =
        api.getPredictions(fixtureId)
}