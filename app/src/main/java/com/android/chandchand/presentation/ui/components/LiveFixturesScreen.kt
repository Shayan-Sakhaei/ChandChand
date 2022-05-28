package com.android.chandchand.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.chandchand.R
import com.android.chandchand.presentation.fixtures.live.LiveFixturesViewModel
import com.android.chandchand.presentation.model.LiveFixturesPerLeagueModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun LiveFixturesScreen(
    viewModel: LiveFixturesViewModel,
) {

    val state by viewModel.state.collectAsState()

    Column {
        ChandChandAppBar(title = stringResource(R.string.ongoing_fixtures))
        LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
            items(state.liveFixtures.entities) { fixtures: LiveFixturesPerLeagueModel ->
                LiveFixturesPerLeague(fixtures = fixtures) { liveFixturesPerLeagueModel: LiveFixturesPerLeagueModel ->
                    viewModel.onLeagueHeaderClick(liveFixturesPerLeagueModel)
                }
            }
        }
    }
}