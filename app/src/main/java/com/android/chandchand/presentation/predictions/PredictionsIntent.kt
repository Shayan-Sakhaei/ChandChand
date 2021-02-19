package com.android.chandchand.presentation.predictions

import com.android.chandchand.presentation.common.IIntent

sealed class PredictionsIntent : IIntent {
    data class GetPredictions(val fixtureId: Int) : PredictionsIntent()
}