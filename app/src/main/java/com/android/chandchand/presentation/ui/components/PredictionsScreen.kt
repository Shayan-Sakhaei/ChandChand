package com.android.chandchand.presentation.ui.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.android.chandchand.R
import com.android.chandchand.presentation.predictions.PredictionsViewModel

@Composable
fun PredictionsScreen(
    viewModel: PredictionsViewModel,
    onHomeDrawableLoad: (Drawable?) -> Unit,
    onAwayDrawableLoad: (Drawable?) -> Unit
) {

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        ChandChandAppBar(title = stringResource(id = R.string.fixture_prediction))
        PredictionHeader(state = state, { onHomeDrawableLoad(it) }, { onAwayDrawableLoad(it) })
        PredictionResult(state = state)
        PredictionGoals(state = state)
        PredictionStatistics(state = state)
    }
}