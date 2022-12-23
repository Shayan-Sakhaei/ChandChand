package com.anonymous.domain.model

import com.anonymous.common.result.Result
import com.anonymous.data.model.PredictionsEntity
import com.anonymous.data.repository.PredictionsRepository
import javax.inject.Inject

class GetPredictionsUseCase @Inject constructor(
    private val predictionsRepository: PredictionsRepository
) {
    suspend fun execute(fixtureId: Int): Result<PredictionsEntity> {
        return predictionsRepository.getPredictions(fixtureId)
    }
}