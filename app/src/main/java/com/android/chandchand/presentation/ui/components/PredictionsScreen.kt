package com.android.chandchand.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.android.chandchand.R
import com.android.chandchand.presentation.predictions.PredictionsViewModel

@Composable
fun PredictionsScreen(
    viewModel: PredictionsViewModel
) {

    val state by viewModel.state.collectAsState()

    Column {
        ChandChandAppBar(title = stringResource(id = R.string.fixture_prediction))
        Card(
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth()
                .height(72.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.surface)
            ) {
                val (homeTeamNameRef, homeTeamLogoRef, dateRef,
                    timeRef, awayTeamLogoRef, awayTeamNameRef) = createRefs()

                createVerticalChain(dateRef, timeRef)

                Text(
                    text = state.date ?: "",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .constrainAs(dateRef) {
                            centerHorizontallyTo(parent)
                        }
                )

                Text(
                    text = state.time ?: "",
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .constrainAs(timeRef) {
                            centerHorizontallyTo(parent)
                        }
                )

                AsyncImage(model = state.homeTeamLogoUrl,
                    contentDescription = "home team logo",
                    contentScale = ContentScale.Inside,
                    placeholder = painterResource(id = R.drawable.ic_flag_placeholder_32),
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .size(40.dp)
                        .constrainAs(homeTeamLogoRef) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(dateRef.start)
                        })

                Text(
                    text = state.predictions?.home_team_name ?: "",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .constrainAs(homeTeamNameRef) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(homeTeamLogoRef.start)
                        }
                )

                AsyncImage(model = state.awayTeamLogoUrl,
                    contentDescription = "away team logo",
                    contentScale = ContentScale.Inside,
                    placeholder = painterResource(id = R.drawable.ic_flag_placeholder_32),
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .size(40.dp)
                        .constrainAs(awayTeamLogoRef) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(dateRef.end)
                        })

                Text(
                    text = state.predictions?.away_team_name ?: "",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .constrainAs(awayTeamNameRef) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(awayTeamLogoRef.end)
                        }
                )
            }
        }
    }
}