package com.android.chandchand.presentation.fixtures.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.chandchand.R
import com.android.chandchand.presentation.fixtures.LiveFixturesIntent
import com.android.chandchand.presentation.fixtures.LiveFixturesState
import com.android.chandchand.presentation.fixtures.LiveFixturesViewModel
import com.android.chandchand.presentation.model.LiveFixturesPerLeagueModel
import com.android.chandchand.presentation.ui.components.ChandChandAppBar
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun LiveFixturesRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: LiveFixturesViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.intents.send(LiveFixturesIntent.GetLiveFixtures)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    LiveFixturesScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        state = state,
        onTvClick = {},
        onLeagueHeaderClick = viewModel::onLeagueHeaderClick
    )
}

@Composable
fun LiveFixturesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    state: LiveFixturesState,
    onTvClick: () -> Unit,
    onLeagueHeaderClick: (LiveFixturesPerLeagueModel) -> Unit
) {
    Column {
        ChandChandAppBar(onBackClick = onBackClick, title = stringResource(R.string.fixtures)) {
            IconButton(onClick = onTvClick) {
                Image(
                    painter = painterResource(id = R.drawable.ic_tv_badge_24),
                    contentDescription = "calendar"
                )
            }
        }

        ChandChandAppBar(
            isTopLevelDestination = true,
            title = stringResource(R.string.ongoing_fixtures)
        )

        LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
            items(state.liveFixtures.entities) { fixtures: LiveFixturesPerLeagueModel ->
                LiveFixturesPerLeague(fixtures = fixtures) { liveFixturesPerLeagueModel: LiveFixturesPerLeagueModel ->
                    onLeagueHeaderClick(liveFixturesPerLeagueModel)
                }
            }
        }
    }
}