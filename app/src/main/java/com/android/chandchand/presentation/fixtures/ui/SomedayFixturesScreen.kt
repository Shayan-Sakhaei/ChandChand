package com.android.chandchand.presentation.fixtures.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.chandchand.R
import com.android.chandchand.presentation.fixtures.FixturesIntent
import com.android.chandchand.presentation.fixtures.FixturesState
import com.android.chandchand.presentation.fixtures.FixturesViewModel
import com.android.chandchand.presentation.model.FixturesPerLeagueModel
import com.android.chandchand.presentation.predictions.navigation.PredictionsDestination
import com.android.chandchand.presentation.ui.components.ChandChandAppBar
import com.android.chandchand.presentation.ui.navigation.ChandChandNavigationDestination
import com.android.chandchand.presentation.utils.DAY
import com.android.chandchand.presentation.utils.toHourMin
import com.android.chandchand.presentation.utils.toPersianDate
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun SomedayFixturesRoute(
    modifier: Modifier = Modifier,
    onNavigate: (ChandChandNavigationDestination, String) -> Unit,
    onBackClick: () -> Unit,
    onCalendarClick: () -> Unit,
    viewModel: FixturesViewModel = hiltViewModel(),
    selectedDate: String,
    selectedDateDescription: String
) {
    LaunchedEffect(true) {
        viewModel.intents.send(FixturesIntent.GetSomedayFixtures(selectedDate))
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    SomedayFixturesScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onNavigate = onNavigate,
        onCalendarClick = onCalendarClick,
        state = state,
        selectedDateDescription = selectedDateDescription,
        onLeagueHeaderClick = viewModel::onLeagueHeaderClick
    )
}

@Composable
fun SomedayFixturesScreen(
    modifier: Modifier = Modifier,
    onNavigate: (ChandChandNavigationDestination, String) -> Unit,
    onBackClick: () -> Unit,
    onCalendarClick: () -> Unit,
    state: FixturesState,
    selectedDateDescription: String,
    onLeagueHeaderClick: (model: FixturesPerLeagueModel, day: DAY) -> Unit
) {
    Column {
        ChandChandAppBar(
            onBackClick = onBackClick,
            title = stringResource(R.string.fixtures)
        ) {
            IconButton(onClick = onCalendarClick) {
                Image(
                    painter = painterResource(id = R.drawable.ic_calendar_badge_24),
                    contentDescription = "calendar"
                )
            }
        }

        ChandChandAppBar(isTopLevelDestination = true, title = selectedDateDescription)

        FixturesPerDay(
            fixtures = state.somedayFixturesState.fixtures,
            onHeaderClick = {
                onLeagueHeaderClick(it, DAY.SOMEDAY)
            },
            onPredictionClick = { fixtureEntity ->
                onNavigate(
                    PredictionsDestination,
                    PredictionsDestination.createNavigationRoute(
                        fixtureEntity.id,
                        fixtureEntity.home_team_logo ?: "",
                        fixtureEntity.away_team_logo ?: "",
                        fixtureEntity.date?.toPersianDate() ?: "",
                        fixtureEntity.timestamp?.toHourMin() ?: ""
                    )
                )
            }
        )
    }
}