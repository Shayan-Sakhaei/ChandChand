package com.android.domain.usecase

import com.android.domain.common.Result
import com.android.domain.entities.PredictionsEntity
import com.android.domain.repositories.PredictionsRepository
import javax.inject.Inject

class GetPredictionsUseCase @Inject constructor(
    private val predictionsRepository: PredictionsRepository
) {
    suspend fun execute(fixtureId: Int): Result<PredictionsEntity> {
        return predictionsRepository.getPredictions(fixtureId)
    }
}