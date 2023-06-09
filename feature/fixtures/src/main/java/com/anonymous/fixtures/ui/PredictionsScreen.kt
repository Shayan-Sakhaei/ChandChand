package com.anonymous.fixtures.ui

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anonymous.designsystem.component.ChandChandAppBar
import com.anonymous.fixtures.HomeAway
import com.anonymous.fixtures.PredictionsIntent
import com.anonymous.fixtures.PredictionsState
import com.anonymous.fixtures.PredictionsViewModel
import com.anonymous.fixtures.R

@Composable
fun PredictionsRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: PredictionsViewModel = hiltViewModel(),
    id: Int,
    homeTeamLogoUrl: String,
    awayTeamLogoUrl: String,
    date: String,
    time: String
) {
    LaunchedEffect(true) {
        viewModel.prepareState(homeTeamLogoUrl, awayTeamLogoUrl, date, time)
        viewModel.intents.send(PredictionsIntent.GetPredictions(id))
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    PredictionsScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        state = state,
        onHomeDrawableLoad = viewModel::palette,
        onAwayDrawableLoad = viewModel::palette
    )
}

@Composable
fun PredictionsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    state: PredictionsState,
    onHomeDrawableLoad: (Drawable?, HomeAway) -> Unit,
    onAwayDrawableLoad: (Drawable?, HomeAway) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        ChandChandAppBar(
            onBackClick = onBackClick,
            title = stringResource(id = R.string.fixture_prediction)
        )
        PredictionHeader(
            state = state,
            { onHomeDrawableLoad(it, HomeAway.HOME) },
            { onAwayDrawableLoad(it, HomeAway.AWAY) })
        PredictionResult(state = state)
        PredictionGoals(state = state)
        PredictionStatistics(state = state)
    }
}