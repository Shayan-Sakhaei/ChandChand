package com.anonymous.fixtures.ui

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
import com.anonymous.designsystem.component.ChandChandAppBar
import com.anonymous.fixtures.FixturesIntent
import com.anonymous.fixtures.FixturesState
import com.anonymous.fixtures.FixturesViewModel
import com.anonymous.fixtures.R
import com.anonymous.fixtures.model.FixturesPerLeagueModel
import com.anonymous.fixtures.navigation.PredictionsDestination
import com.anonymous.ui.extension.toHourMin
import com.anonymous.ui.extension.toPersianDate
import com.anonymous.ui.model.DAY
import com.anonymous.ui.navigation.ChandChandNavigationDestination
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