package com.anonymous.leagues.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.anonymous.designsystem.theme.ChandChandTheme
import com.anonymous.leagues.R
import com.anonymous.leagues.model.LeagueTitleModel
import com.anonymous.leagues.navigation.StandingsDestination
import com.anonymous.ui.navigation.ChandChandNavigationDestination

@Composable
fun LeagueTitle(
    modifier: Modifier = Modifier,
    leagueTitleModel: LeagueTitleModel,
    onNavigate: (ChandChandNavigationDestination, String) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
            .fillMaxWidth()
            .height(64.dp)
            .clickable {
                onNavigate(
                    StandingsDestination,
                    StandingsDestination.createNavigationRoute(
                        leagueTitleModel.id, leagueTitleModel.titleResId
                    )
                )
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.padding(start = 12.dp),
                painter = painterResource(id = leagueTitleModel.logoResId),
                contentDescription = "${stringResource(id = leagueTitleModel.titleResId)} logo"
            )

            Text(
                text = stringResource(id = leagueTitleModel.titleResId), modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .weight(1f),
                textAlign = TextAlign.Start
            )

            Image(
                modifier = Modifier.padding(end = 16.dp),
                painter = painterResource(
                    id = if (LocalLayoutDirection.current == LayoutDirection.Rtl) R.drawable.ic_arrow_left_24 else R.drawable.ic_arrow_right_24
                ),
                contentDescription = "arrow_left_right"
            )
        }
    }
}


@Composable
@Preview
private fun PreviewLeagueTitle() {
    ChandChandTheme {
        LeagueTitle(
            leagueTitleModel = LeagueTitleModel(
                R.string.persian_gulf_cup,
                R.drawable.ic_persian_gulf_cup_32, 3030
            )
        ) { destination: ChandChandNavigationDestination, route: String -> }
    }
}