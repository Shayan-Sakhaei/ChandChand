package com.anonymous.leagues.ui

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anonymous.data.model.StandingEntity
import com.anonymous.designsystem.component.ChandChandAppBar
import com.anonymous.designsystem.theme.ChandChandTheme
import com.anonymous.leagues.LeaguesIntent
import com.anonymous.leagues.R
import com.anonymous.leagues.StandingsState
import com.anonymous.leagues.StandingsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
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
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .width(32.dp),
                text = stringResource(id = R.string.match),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .width(26.dp),
                text = stringResource(id = R.string.win),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .width(40.dp),
                text = stringResource(id = R.string.draw),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .width(32.dp),
                text = stringResource(id = R.string.lose),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .width(38.dp),
                text = stringResource(id = R.string.difference),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .width(32.dp),
                text = stringResource(id = R.string.point),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
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

@Composable
@Preview(name = "Fixture", showBackground = true)
private fun PreviewFixture() {
    ChandChandTheme {
        StandingsScreen(
            onBackClick = {},
            state = StandingsState(
                standings = listOf(
                    StandingEntity(
                        1,
                        0,
                        "Perspolis",
                        "",
                        "",
                        "",
                        "",
                        "",
                        10,
                        7,
                        1,
                        2,
                        20,
                        7,
                        4,
                        3,
                        1,
                        2,
                        12,
                        5,
                        10,
                        4,
                        3,
                        1,
                        2,
                        12,
                        5,
                        10,
                        ""
                    ),
                )
            ), leagueTitleResId = R.string.persian_gulf_cup
        )
    }
}