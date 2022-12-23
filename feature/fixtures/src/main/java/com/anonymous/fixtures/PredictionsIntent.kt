package com.anonymous.fixtures

import com.anonymous.ui.model.IIntent

sealed class PredictionsIntent : IIntent {
    data class GetPredictions(val fixtureId: Int) : PredictionsIntent()
}