package com.android.chandchand.presentation.ui.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.chandchand.R
import com.android.chandchand.presentation.model.LeagueTitleModel
import com.android.chandchand.presentation.ui.navigation.ChandChandNavigationDestination
import com.android.chandchand.presentation.ui.navigation.StandingsDestination
import com.android.chandchand.presentation.ui.theme.ChandChandTheme

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
                modifier = Modifier.padding(start = 16.dp),
                painter = painterResource(id = R.drawable.ic_arrow_left_24),
                contentDescription = "arrow left"
            )

            Text(
                text = stringResource(id = leagueTitleModel.titleResId), modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .weight(1f),
                textAlign = TextAlign.End
            )

            Image(
                modifier = Modifier.padding(end = 12.dp),
                painter = painterResource(id = leagueTitleModel.logoResId),
                contentDescription = "${stringResource(id = leagueTitleModel.titleResId)} logo"
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