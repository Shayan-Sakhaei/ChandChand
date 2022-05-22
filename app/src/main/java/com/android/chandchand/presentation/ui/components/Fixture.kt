package com.android.chandchand.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.android.chandchand.R
import com.android.chandchand.data.fixtures.entity.*
import com.android.chandchand.data.fixtures.mapper.FixtureServerEntityMapper
import com.android.chandchand.domain.entities.FixtureEntity
import com.android.chandchand.presentation.model.FixturesPerLeagueModel
import com.android.chandchand.presentation.model.LeagueModel
import com.android.chandchand.presentation.theme.ChandChandTheme
import com.android.chandchand.presentation.utils.*

@Composable
fun Fixture(fixture: FixtureEntity) {

    Card(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
            .fillMaxWidth()
            .height(if (fixture.status_short == NOT_STARTED) 112.dp else 144.dp),
        elevation = 4.dp
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            val (homeTeamLogo, homeTeamName, homeTeamGoals,
                predictionIcon, timeText, statusText,
                awayTeamLogo, awayTeamName, awayTeamGoals) = createRefs()

            Image(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(40.dp)
                    .constrainAs(predictionIcon) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                painter = painterResource(id = R.drawable.ic_prediction_with_border_40),
                contentDescription = "prediction icon"
            )

            AsyncImage(model = fixture.home_team_logo,
                contentDescription = "home team logo",
                contentScale = ContentScale.Inside,
                placeholder = painterResource(id = R.drawable.ic_flag_placeholder_32),
                modifier = Modifier
                    .padding(start = 48.dp, top = 16.dp)
                    .size(48.dp)
                    .constrainAs(homeTeamLogo) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    })

            Text(
                text = fixture.home_team_name ?: "",
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 14.dp, top = 12.dp)
                    .size(116.dp, 24.dp)
                    .constrainAs(homeTeamName) {
                        start.linkTo(parent.start)
                        top.linkTo(homeTeamLogo.bottom)
                    })

            AsyncImage(model = fixture.away_team_logo,
                contentDescription = "away team logo",
                contentScale = ContentScale.Inside,
                placeholder = painterResource(id = R.drawable.ic_flag_placeholder_32),
                modifier = Modifier
                    .padding(end = 48.dp, top = 16.dp)
                    .size(48.dp)
                    .constrainAs(awayTeamLogo) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    })

            Text(
                text = fixture.away_team_name ?: "",
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(end = 14.dp, top = 12.dp)
                    .size(116.dp, 24.dp)
                    .constrainAs(awayTeamName) {
                        end.linkTo(parent.end)
                        top.linkTo(awayTeamLogo.bottom)
                    })

            when (fixture.status_short) {
                NOT_STARTED -> {
                    //TIME
                    Text(
                        text = fixture.timestamp?.toHourMin() ?: "",
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .constrainAs(timeText) {
                                start.linkTo(predictionIcon.start)
                                end.linkTo(predictionIcon.end)
                                top.linkTo(predictionIcon.bottom)
                            })
                }
                MATCH_POSTPONED -> {
                    //STATUS
                    Text(
                        text = stringResource(id = R.string.postponed),
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 48.dp)
                            .constrainAs(statusText) {
                                start.linkTo(predictionIcon.start)
                                end.linkTo(predictionIcon.end)
                                top.linkTo(predictionIcon.bottom)
                            })

                    //HOME GOALS
                    Text(
                        text = "?",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 14.dp)
                            .constrainAs(homeTeamGoals) {
                                start.linkTo(homeTeamName.start)
                                end.linkTo(homeTeamName.end)
                                top.linkTo(homeTeamName.bottom)
                                bottom.linkTo(parent.bottom)
                            })

                    //AWAY GOALS
                    Text(
                        text = "?",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(end = 14.dp)
                            .constrainAs(awayTeamGoals) {
                                start.linkTo(awayTeamName.start)
                                end.linkTo(awayTeamName.end)
                                top.linkTo(awayTeamName.bottom)
                                bottom.linkTo(parent.bottom)
                            })
                }
                MATCH_CANCELLED -> {
                    //STATUS
                    Text(
                        text = stringResource(id = R.string.cancelled),
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 48.dp)
                            .constrainAs(statusText) {
                                start.linkTo(predictionIcon.start)
                                end.linkTo(predictionIcon.end)
                                top.linkTo(predictionIcon.bottom)
                            })

                    //HOME GOALS
                    Text(
                        text = "_",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 14.dp)
                            .constrainAs(homeTeamGoals) {
                                start.linkTo(homeTeamName.start)
                                end.linkTo(homeTeamName.end)
                                top.linkTo(homeTeamName.bottom)
                                bottom.linkTo(parent.bottom)
                            })

                    //AWAY GOALS
                    Text(
                        text = "_",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(end = 14.dp)
                            .constrainAs(awayTeamGoals) {
                                start.linkTo(awayTeamName.start)
                                end.linkTo(awayTeamName.end)
                                top.linkTo(awayTeamName.bottom)
                                bottom.linkTo(parent.bottom)
                            })
                }
                MATCH_FINISHED -> {
                    //TIME
                    Text(
                        text = fixture.timestamp?.toHourMin() ?: "",
                        style = MaterialTheme.typography.body1.copy(
                            textDecoration = TextDecoration.LineThrough
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .constrainAs(timeText) {
                                start.linkTo(predictionIcon.start)
                                end.linkTo(predictionIcon.end)
                                top.linkTo(predictionIcon.bottom)
                            })

                    //STATUS
                    Text(
                        text = stringResource(id = R.string.match_finished),
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 48.dp)
                            .constrainAs(statusText) {
                                start.linkTo(predictionIcon.start)
                                end.linkTo(predictionIcon.end)
                                top.linkTo(predictionIcon.bottom)
                            })

                    //HOME GOALS
                    Text(
                        text = fixture.goals_home ?: "",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 14.dp)
                            .constrainAs(homeTeamGoals) {
                                start.linkTo(homeTeamName.start)
                                end.linkTo(homeTeamName.end)
                                top.linkTo(homeTeamName.bottom)
                                bottom.linkTo(parent.bottom)
                            })

                    //AWAY GOALS
                    Text(
                        text = fixture.goals_away ?: "",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(end = 14.dp)
                            .constrainAs(awayTeamGoals) {
                                start.linkTo(awayTeamName.start)
                                end.linkTo(awayTeamName.end)
                                top.linkTo(awayTeamName.bottom)
                                bottom.linkTo(parent.bottom)
                            })
                }
                else -> {
                    //TIME
                    Text(
                        text = fixture.timestamp?.toHourMin() ?: "",
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .constrainAs(timeText) {
                                start.linkTo(predictionIcon.start)
                                end.linkTo(predictionIcon.end)
                                top.linkTo(predictionIcon.bottom)
                            })

                    //STATUS
                    Text(
                        text = stringResource(id = R.string.ongoing),
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 48.dp)
                            .constrainAs(statusText) {
                                start.linkTo(predictionIcon.start)
                                end.linkTo(predictionIcon.end)
                                top.linkTo(predictionIcon.bottom)
                            })

                    //HOME GOALS
                    Text(
                        text = fixture.goals_home ?: "",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 14.dp)
                            .constrainAs(homeTeamGoals) {
                                start.linkTo(homeTeamName.start)
                                end.linkTo(homeTeamName.end)
                                top.linkTo(homeTeamName.bottom)
                                bottom.linkTo(parent.bottom)
                            })

                    //AWAY GOALS
                    Text(
                        text = fixture.goals_away ?: "",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(end = 14.dp)
                            .constrainAs(awayTeamGoals) {
                                start.linkTo(awayTeamName.start)
                                end.linkTo(awayTeamName.end)
                                top.linkTo(awayTeamName.bottom)
                                bottom.linkTo(parent.bottom)
                            })
                }
            }
        }
    }
}

@Composable
@Preview(name = "Fixture", showBackground = true)
private fun PreviewFixture() {
    ChandChandTheme {
        Fixture(PreviewData.fixture)
    }
}

object PreviewData {
    private val fixturesMapper = FixtureServerEntityMapper()
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

    val fixture = fixturesMapper.map(fixFixture)
    val fixturesPerLeague = FixturesPerLeagueModel(
        LeagueModel(R.drawable.ic_persian_gulf_cup_32, R.string.persian_gulf_cup, 3),
        listOf(fixture, fixture, fixture),
        true
    )
}