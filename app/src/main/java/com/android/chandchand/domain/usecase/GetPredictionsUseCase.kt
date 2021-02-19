package com.android.chandchand.domain.usecase

import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.entities.PredictionsEntity
import com.android.chandchand.domain.repositories.PredictionsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPredictionsUseCase @Inject constructor(
    private val predictionsRepository: PredictionsRepository
) {
    suspend fun execute(fixtureId: Int): Flow<Result<PredictionsEntity>> {
        return predictionsRepository.getPredictions(fixtureId)
    }
}