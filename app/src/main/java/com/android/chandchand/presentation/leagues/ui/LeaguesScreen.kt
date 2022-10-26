package com.android.chandchand.presentation.leagues.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.chandchand.R
import com.android.chandchand.presentation.model.LeagueTitleModel
import com.android.chandchand.presentation.ui.components.ChandChandAppBar
import com.android.chandchand.presentation.ui.navigation.ChandChandNavigationDestination

@Composable
fun LeaguesRoute(
    modifier: Modifier = Modifier,
    onNavigate: (ChandChandNavigationDestination, String) -> Unit,
) {
    LeaguesScreen(
        modifier = modifier,
        onNavigate = onNavigate
    )
}

@Composable
fun LeaguesScreen(
    modifier: Modifier = Modifier,
    onNavigate: (ChandChandNavigationDestination, String) -> Unit
) {
    Column {
        ChandChandAppBar(
            isTopLevelDestination = true,
            title = stringResource(id = R.string.leagues)
        )
        LazyColumn(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
        ) {
            items(
                items = leaguesTitleList,
                key = { it.id }
            ) { leagueTitleModel: LeagueTitleModel ->
                LeagueTitle(
                    modifier,
                    leagueTitleModel
                ) { destination: ChandChandNavigationDestination, route: String ->
                    onNavigate(destination, route)
                }
            }
        }
    }
}

val leaguesTitleList =
    listOf(
        LeagueTitleModel(
            R.string.persian_gulf_cup,
            R.drawable.ic_persian_gulf_cup_32,
            4640
        ),
        LeagueTitleModel(
            R.string.english_premier_league,
            R.drawable.ic_premier_league_32,
            4335
        ),
        LeagueTitleModel(
            R.string.spanish_la_liga,
            R.drawable.ic_la_liga_32,
            4378
        ),
        LeagueTitleModel(
            R.string.italian_serie_a,
            R.drawable.ic_serie_a_32,
            4399
        ),
        LeagueTitleModel(
            R.string.german_bundesliga_1,
            R.drawable.ic_bundesliga_32,
            4346
        ),
        LeagueTitleModel(
            R.string.french_ligue_1,
            R.drawable.ic_ligue_1_32,
            4347
        )
    )