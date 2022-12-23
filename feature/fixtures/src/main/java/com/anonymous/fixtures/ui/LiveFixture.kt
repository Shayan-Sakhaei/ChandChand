package com.anonymous.fixtures.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.anonymous.fixtures.R
import com.anonymous.ui.extension.toHourMin
import com.anonymous.ui.model.MATCH_CANCELLED
import com.anonymous.ui.model.MATCH_FINISHED
import com.anonymous.ui.model.MATCH_POSTPONED
import com.anonymous.ui.model.NOT_STARTED

@Composable
fun LiveFixture(fixture: com.anonymous.data.model.LiveFixtureEntity) {

    Card(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
            .fillMaxWidth()
            .height(if (fixture.status_short == NOT_STARTED) 112.dp else 144.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
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
                style = MaterialTheme.typography.bodyMedium,
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
                style = MaterialTheme.typography.bodyMedium,
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
                        style = MaterialTheme.typography.bodyLarge,
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
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
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
                        style = MaterialTheme.typography.headlineSmall,
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
                        style = MaterialTheme.typography.headlineSmall,
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
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
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
                        style = MaterialTheme.typography.headlineSmall,
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
                        style = MaterialTheme.typography.headlineSmall,
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
                        style = MaterialTheme.typography.bodyLarge.copy(
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
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
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
                        style = MaterialTheme.typography.headlineSmall,
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
                        style = MaterialTheme.typography.headlineSmall,
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
                        style = MaterialTheme.typography.bodyLarge,
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
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
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
                        style = MaterialTheme.typography.headlineSmall,
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
                        style = MaterialTheme.typography.headlineSmall,
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