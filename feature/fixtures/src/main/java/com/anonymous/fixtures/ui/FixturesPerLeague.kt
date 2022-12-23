package com.anonymous.fixtures.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anonymous.data.model.FixtureEntity
import com.anonymous.designsystem.theme.ChandChandTheme
import com.anonymous.fixtures.R
import com.anonymous.fixtures.model.FixturesPerLeagueModel
import com.anonymous.fixtures.model.LeagueHeaderModel
import com.anonymous.network.model.*

@Composable
fun FixturesPerLeague(
    fixtures: FixturesPerLeagueModel,
    onHeaderClick: (FixturesPerLeagueModel) -> Unit,
    onPredictionClick: (FixtureEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(64.dp)
            .animateContentSize(animationSpec = tween()),
    ) {
        Card(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
                .fillMaxWidth()
                .height(64.dp)
                .clickable { onHeaderClick(fixtures) },
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
                    painter = painterResource(id = fixtures.leagueHeaderModel.leagueLogo),
                    contentDescription = "${stringResource(id = fixtures.leagueHeaderModel.leagueTitle)} logo"
                )

                Text(
                    text = stringResource(id = fixtures.leagueHeaderModel.leagueTitle),
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .weight(1f),
                    textAlign = TextAlign.Start
                )

                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(32.dp)
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(
                        text = "${fixtures.leagueHeaderModel.fixturesCount}",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Image(
                    modifier = Modifier.padding(end = 16.dp),
                    painter = painterResource(
                        id = if (fixtures.isExpanded) R.drawable.ic_drop_down_arrow_up_24 else R.drawable.ic_drop_down_arrow_down_24
                    ),
                    contentDescription = "arrow_up_or_down"
                )
            }
        }

        if (fixtures.isExpanded) {
            Column {
                fixtures.fixtures.forEach { fixtureEntity ->
                    Fixture(
                        fixture = fixtureEntity,
                        onPredictionClick = { onPredictionClick(it) }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(name = "FixturesPerLeague", showBackground = true)
private fun PreviewFixturesPerLeague() {
    ChandChandTheme {
        var fixtures by remember { mutableStateOf(fixturesPerLeague) }
        FixturesPerLeague(fixtures, {
            val prevState = fixtures.isExpanded
            fixtures = fixtures.copy(isExpanded = !prevState)
        }, {})
    }
}

private val fixturesMapper = com.anonymous.data.mapper.FixtureServerEntityMapper()
private val fixFixture =
    FixFixtures(
        fixture_id = 844125,
        league_id = 3030,
        league = FixLeague(),
        event_date = "2022-03-17T14:00:00+00:00",
        event_timestamp = 1647525600,
        firstHalfStart = "1647525600",
        secondHalfStart = "1647529200",
        round = "Regular Season - 23",
        status = "Match Finished",
        statusShort = "FT",
        elapsed = 90,
        venue = "Azadi Stadium",
        referee = "M. Seyedali",
        homeTeam = FixHomeTeam(
            2742,
            "Persepolis FC",
            "https://media.api-sports.io/football/teams/2742.png"
        ),
        awayTeam = FixAwayTeam(
            2733,
            "Esteghlal FC",
            "https://media.api-sports.io/football/teams/2733.png"
        ),
        goalsHomeTeam = "1",
        goalsAwayTeam = "1",
        score = FixScore()
    )

private val fixture = fixturesMapper.map(fixFixture)
val fixturesPerLeague = FixturesPerLeagueModel(
    LeagueHeaderModel(
        R.drawable.ic_persian_gulf_cup_32,
        R.string.persian_gulf_cup,
        3
    ),
    listOf(fixture, fixture, fixture),
    true
)