package com.android.chandchand.presentation.ui.components

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

@Composable
fun LeaguesScreen(onLeagueTitleClick: (LeagueTitleModel) -> Unit) {
    Column {
        ChandChandAppBar(title = stringResource(id = R.string.leagues))
        LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
            items(leaguesTitleList, key = { it.id }) { leagueTitleModel: LeagueTitleModel ->
                LeagueTitle(leagueTitleModel) { model: LeagueTitleModel ->
                    onLeagueTitleClick(model)
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
            3030
        ),
        LeagueTitleModel(
            R.string.english_premier_league,
            R.drawable.ic_premier_league_32,
            2790
        ),
        LeagueTitleModel(
            R.string.spanish_la_liga,
            R.drawable.ic_la_liga_32,
            2833
        ),
        LeagueTitleModel(
            R.string.italian_serie_a,
            R.drawable.ic_serie_a_32,
            2857
        ),
        LeagueTitleModel(
            R.string.german_bundesliga_1,
            R.drawable.ic_bundesliga_32,
            2755
        ),
        LeagueTitleModel(
            R.string.french_ligue_1,
            R.drawable.ic_ligue_1_32,
            2664
        )
    )