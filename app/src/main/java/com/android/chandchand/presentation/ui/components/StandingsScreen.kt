package com.android.chandchand.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.chandchand.R
import com.android.chandchand.domain.entities.StandingEntity
import com.android.chandchand.presentation.leagues.LeaguesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun StandingsScreen(
    viewModel: LeaguesViewModel,
    @StringRes leagueTitleResId: Int
) {

    val state by viewModel.state.collectAsState()

    Column {
        ChandChandAppBar(title = stringResource(leagueTitleResId))
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
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.primaryVariant
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .width(38.dp),
                text = stringResource(id = R.string.difference),
                style = MaterialTheme.typography.caption
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .width(32.dp),
                text = stringResource(id = R.string.lose),
                style = MaterialTheme.typography.caption
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .width(40.dp),
                text = stringResource(id = R.string.draw),
                style = MaterialTheme.typography.caption
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .width(26.dp),
                text = stringResource(id = R.string.win),
                style = MaterialTheme.typography.caption
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .width(32.dp),
                text = stringResource(id = R.string.match),
                style = MaterialTheme.typography.caption
            )
        }
        Divider(color = Color.Black.copy(alpha = .10f), thickness = 1.dp)
        LazyColumn {
            items(state.standings, key = { it.team_id }) { standingEntity: StandingEntity ->
                Standing(standing = standingEntity)
            }
        }
    }
}
