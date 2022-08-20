@file:JvmName("PredictionGoalKt")

package com.android.chandchand.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.android.chandchand.R
import com.android.chandchand.presentation.predictions.PredictionsState
import com.android.chandchand.presentation.theme.ChandChandTheme
import com.android.chandchand.presentation.theme.DarkSurfaceInfo
import com.android.chandchand.presentation.theme.LightSurfaceInfo

@Composable
fun PredictionGoals(state: PredictionsState) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .fillMaxWidth()
            .height(310.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            val (
                goalsPredictionRef, homeTeamLogoRef, homeTeamNameRef, homeTeamGoalsText,
                awayTeamLogoRef, awayTeamNameRef, awayTeamGoalsText, vsRef,
                verticalDividerRef, horizontalDividerRef, matchGoalsTitleRef, matchGoalsTextRef
            ) = createRefs()

            //MATCH RESULT
            Text(
                text = stringResource(id = R.string.goals_prediction),
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .constrainAs(goalsPredictionRef) {
                        top.linkTo(parent.top)
                        centerHorizontallyTo(parent)
                    }
            )

            //AWAY LOGO
            AsyncImage(model = state.awayTeamLogoUrl,
                contentDescription = "away logo",
                contentScale = ContentScale.Inside,
                placeholder = painterResource(id = R.drawable.ic_flag_placeholder_32),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(56.dp)
                    .constrainAs(awayTeamLogoRef) {
                        top.linkTo(goalsPredictionRef.bottom)
                    })

            //HOME LOGO
            AsyncImage(model = state.homeTeamLogoUrl,
                contentDescription = "home logo",
                contentScale = ContentScale.Inside,
                placeholder = painterResource(id = R.drawable.ic_flag_placeholder_32),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(56.dp)
                    .constrainAs(homeTeamLogoRef) {
                        top.linkTo(goalsPredictionRef.bottom)
                    })

            //VS
            Image(
                painter = painterResource(id = R.drawable.ic_vs_24),
                contentDescription = "vs",
                modifier = Modifier
                    .padding(top = 34.dp)
                    .size(24.dp)
                    .constrainAs(vsRef) {
                        top.linkTo(goalsPredictionRef.bottom)
                    }
            )

            createHorizontalChain(awayTeamLogoRef, vsRef, homeTeamLogoRef)

            //VERTICAL DIVIDER
            VerticalDivider(modifier = Modifier
                .padding(top = 22.dp)
                .height(72.dp)
                .constrainAs(verticalDividerRef) {
                    top.linkTo(vsRef.bottom)
                    centerHorizontallyTo(parent)
                })


            //AWAY NAME
            Text(
                text = state.predictions?.away_team_name ?: "?",
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .constrainAs(awayTeamNameRef) {
                        top.linkTo(awayTeamLogoRef.bottom)
                        start.linkTo(awayTeamLogoRef.start)
                        end.linkTo(awayTeamLogoRef.end)
                    }
            )

            //HOME NAME
            Text(
                text = state.predictions?.home_team_name ?: "?",
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .constrainAs(homeTeamNameRef) {
                        top.linkTo(homeTeamLogoRef.bottom)
                        start.linkTo(homeTeamLogoRef.start)
                        end.linkTo(homeTeamLogoRef.end)
                    }
            )

            PredictionGoal(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .constrainAs(awayTeamGoalsText) {
                        top.linkTo(awayTeamNameRef.bottom)
                        start.linkTo(awayTeamNameRef.start)
                        end.linkTo(awayTeamNameRef.end)
                    },
                color = state.awayTeamColor
                    ?: if (isSystemInDarkTheme()) LightSurfaceInfo else DarkSurfaceInfo,
                goals = state.predictions?.goals_away ?: "-"
            )

            PredictionGoal(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .constrainAs(homeTeamGoalsText) {
                        top.linkTo(homeTeamNameRef.bottom)
                        start.linkTo(homeTeamNameRef.start)
                        end.linkTo(homeTeamNameRef.end)
                    },
                color = state.homeTeamColor
                    ?: if (isSystemInDarkTheme()) LightSurfaceInfo else DarkSurfaceInfo,
                goals = state.predictions?.goals_home ?: "-"
            )

            //HORIZONTAL DIVIDER
            Divider(modifier = Modifier
                .padding(top = 28.dp, start = 12.dp, end = 12.dp)
                .constrainAs(horizontalDividerRef) {
                    top.linkTo(verticalDividerRef.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            //MATCH GOALS TITLE
            Text(
                text = stringResource(id = R.string.match_goals_sum),
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .constrainAs(matchGoalsTitleRef) {
                        top.linkTo(horizontalDividerRef.bottom)
                        centerHorizontallyTo(parent)
                    }
            )

            Box(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .constrainAs(matchGoalsTextRef) {
                        top.linkTo(matchGoalsTitleRef.bottom)
                        centerHorizontallyTo(parent)
                    }
                    .size(width = 54.dp, height = 30.dp)
                    .background(
                        color = if (isSystemInDarkTheme()) DarkSurfaceInfo else LightSurfaceInfo,
                        shape = RoundedCornerShape(15.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.predictions?.under_over ?: "N/A",
                    style = MaterialTheme.typography.overline,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun PredictionGoal(
    modifier: Modifier = Modifier,
    color: Color,
    goals: String
) {
    Surface(
        modifier = modifier.size(width = 72.dp, height = 30.dp),
        shape = RoundedCornerShape(15.dp),
        color = color.copy(alpha = .15f)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = goals,
                style = MaterialTheme.typography.overline,
                textAlign = TextAlign.Center,
            )

            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_ball_16),
                contentDescription = "ball icon",
                colorFilter = ColorFilter.tint(color)
            )
        }
    }
}

@Composable
@Preview(name = "Prediction Goals", showBackground = true)
private fun PreviewPredictionGoals() {
    ChandChandTheme {
        PredictionGoals(PredictionsState())
    }
}

@Composable
@Preview(name = "Prediction Goal", showBackground = true)
private fun PreviewPredictionUnderOver() {
    ChandChandTheme {
        PredictionGoal(color = Color(0xFFED1B24), goals = "+3.5")
    }
}
