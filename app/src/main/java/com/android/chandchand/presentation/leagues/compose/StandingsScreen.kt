package com.android.chandchand.presentation.leagues.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.chandchand.R
import com.android.chandchand.presentation.leagues.LeaguesIntent
import com.android.chandchand.presentation.leagues.StandingsState
import com.android.chandchand.presentation.leagues.StandingsViewModel
import com.android.chandchand.presentation.ui.components.ChandChandAppBar
import com.android.chandchand.presentation.ui.components.Standing
import com.android.domain.entities.StandingEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(
    ExperimentalCoroutinesApi::class,
    ExperimentalLifecycleComposeApi::class
)
@Composable
fun StandingsRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: StandingsViewModel = hiltViewModel(),
    leagueId: Int,
    leagueTitleResId: Int
) {
    LaunchedEffect(true) {
        viewModel.intents.send(LeaguesIntent.GetStandings(leagueId))
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    StandingsScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        state = state,
        leagueTitleResId = leagueTitleResId
    )
}

@Composable
fun StandingsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    state: StandingsState,
    @StringRes leagueTitleResId: Int
) {
    Column {
        ChandChandAppBar(
            onBackClick = onBackClick,
            title = stringResource(leagueTitleResId)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .width(32.dp),
                text = stringResource(id = R.string.point),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .width(38.dp),
                text = stringResource(id = R.string.difference),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .width(32.dp),
                text = stringResource(id = R.string.lose),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .width(40.dp),
                text = stringResource(id = R.string.draw),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .width(26.dp),
                text = stringResource(id = R.string.win),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .width(32.dp),
                text = stringResource(id = R.string.match),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Divider(color = Color.Black.copy(alpha = .10f), thickness = 1.dp)
        LazyColumn {
            items(
                state.standings,
                key = { it.team_id }) { standingEntity: StandingEntity ->
                Standing(standing = standingEntity)
            }
        }
    }
}
