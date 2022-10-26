package com.android.chandchand.presentation.fixtures.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.chandchand.R
import com.android.chandchand.presentation.fixtures.FixturesIntent
import com.android.chandchand.presentation.fixtures.FixturesState
import com.android.chandchand.presentation.fixtures.FixturesViewModel
import com.android.chandchand.presentation.fixtures.navigation.LiveFixturesDestination
import com.android.chandchand.presentation.model.FixturesPerLeagueModel
import com.android.chandchand.presentation.predictions.navigation.PredictionsDestination
import com.android.chandchand.presentation.ui.components.ChandChandAppBar
import com.android.chandchand.presentation.ui.navigation.ChandChandNavigationDestination
import com.android.chandchand.presentation.ui.theme.ChandChandTheme
import com.android.chandchand.presentation.ui.theme.DarkAppBar
import com.android.chandchand.presentation.ui.theme.LightAppBar
import com.android.chandchand.presentation.utils.DAY
import com.android.chandchand.presentation.utils.toHourMin
import com.android.chandchand.presentation.utils.toPersianDate
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun FixturesRoute(
    modifier: Modifier = Modifier,
    viewModel: FixturesViewModel = hiltViewModel(),
    onNavigate: (ChandChandNavigationDestination, String) -> Unit,
    onCalendarClick: () -> Unit,
) {
    LaunchedEffect(true) {
        viewModel.intents.send(FixturesIntent.GetFixtures)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    FixturesScreen(
        modifier = modifier,
        state = state,
        onNavigate = onNavigate,
        onCalendarClick = onCalendarClick,
        onLeagueHeaderClick = viewModel::onLeagueHeaderClick
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FixturesScreen(
    modifier: Modifier = Modifier,
    state: FixturesState,
    onNavigate: (ChandChandNavigationDestination, String) -> Unit,
    onCalendarClick: () -> Unit,
    onLeagueHeaderClick: (model: FixturesPerLeagueModel, day: DAY) -> Unit
) {
    val tabs = listOf(
        stringResource(id = R.string.yesterday),
        stringResource(id = R.string.today),
        stringResource(id = R.string.tomorrow),
        stringResource(id = R.string.day_after_tomorrow)
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        ChandChandAppBar(
            isTopLevelDestination = true,
            title = stringResource(R.string.fixtures)
        ) {
            IconButton(onClick = { onCalendarClick() }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_calendar_24),
                    contentDescription = "calendar"
                )
            }
            IconButton(onClick = {
                onNavigate(LiveFixturesDestination, LiveFixturesDestination.route)
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_tv_24),
                    contentDescription = "live"
                )
            }
        }
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = if (isSystemInDarkTheme()) DarkAppBar else LightAppBar,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(4.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary))
                )
            }
        ) {
            tabs.forEachIndexed { index, text ->
                Tab(
                    modifier = Modifier.semantics { contentDescription = text },
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(text = text, color = MaterialTheme.colorScheme.onPrimary) })
            }
        }
        HorizontalPager(
            count = tabs.size,
            modifier = Modifier
                .weight(1f)
                .semantics { contentDescription = "HorizontalPager" },
            state = pagerState,
            key = { page -> tabs[page] },
            verticalAlignment = Alignment.Top
        ) {
            when (currentPage) {
                0 -> {
                    FixturesPerDay(
                        fixtures = state.yesterdayFixturesState.fixtures,
                        onHeaderClick = {
                            onLeagueHeaderClick(it, DAY.YESTERDAY)
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
                1 -> {
                    FixturesPerDay(
                        fixtures = state.todayFixturesState.fixtures,
                        onHeaderClick = {
                            onLeagueHeaderClick(it, DAY.TODAY)
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
                2 -> {
                    FixturesPerDay(
                        fixtures = state.tomorrowFixturesState.fixtures,
                        onHeaderClick = {
                            onLeagueHeaderClick(it, DAY.TOMORROW)
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
                3 -> {
                    FixturesPerDay(
                        fixtures = state.dayAfterTomorrowFixturesState.fixtures,
                        onHeaderClick = {
                            onLeagueHeaderClick(it, DAY.DAY_AFTER_TOMORROW)
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
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewFixturesScreen() {
    ChandChandTheme {}
}