package com.android.chandchand.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.chandchand.R
import com.android.chandchand.presentation.predictions.PredictionsState
import com.android.chandchand.presentation.ui.theme.*

@Composable
fun PredictionStatistics(state: PredictionsState) {

    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 20.dp)
            .fillMaxWidth()
            .height(370.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //HEAD TO HEAD
            Text(
                text = stringResource(id = R.string.statistics),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 12.dp)
            )

            //FORM
            PredictionStatistic(
                title = stringResource(id = R.string.form),
                homeTeamPercentText = state.predictions?.comparison_home_forme ?: "N/A",
                awayTeamPercentText = state.predictions?.comparison_away_forme ?: "N/A",
                homeTeamPercentNumber = state.predictions?.comparison_home_forme_number ?: 0,
                awayTeamPercentNumber = state.predictions?.comparison_away_forme_number ?: 0,
                homeTeamColor = state.homeTeamColor
                    ?: if (isSystemInDarkTheme()) DarkSurfaceInfo else LightSurfaceInfo,
                awayTeamColor = state.awayTeamColor
                    ?: if (isSystemInDarkTheme()) DarkSurfaceInfo else LightSurfaceInfo
            )

            //ATTACKING
            PredictionStatistic(
                title = stringResource(id = R.string.attacking_potential),
                homeTeamPercentText = state.predictions?.comparison_home_att ?: "N/A",
                awayTeamPercentText = state.predictions?.comparison_away_att ?: "N/A",
                homeTeamPercentNumber = state.predictions?.comparison_home_att_number ?: 0,
                awayTeamPercentNumber = state.predictions?.comparison_away_att_number ?: 0,
                homeTeamColor = state.homeTeamColor
                    ?: if (isSystemInDarkTheme()) DarkSurfaceInfo else LightSurfaceInfo,
                awayTeamColor = state.awayTeamColor
                    ?: if (isSystemInDarkTheme()) DarkSurfaceInfo else LightSurfaceInfo
            )

            //DEFENSIVE
            PredictionStatistic(
                title = stringResource(id = R.string.defensive_potential),
                homeTeamPercentText = state.predictions?.comparison_home_def ?: "N/A",
                awayTeamPercentText = state.predictions?.comparison_away_def ?: "N/A",
                homeTeamPercentNumber = state.predictions?.comparison_home_def_number ?: 0,
                awayTeamPercentNumber = state.predictions?.comparison_away_def_number ?: 0,
                homeTeamColor = state.homeTeamColor
                    ?: if (isSystemInDarkTheme()) DarkSurfaceInfo else LightSurfaceInfo,
                awayTeamColor = state.awayTeamColor
                    ?: if (isSystemInDarkTheme()) DarkSurfaceInfo else LightSurfaceInfo
            )

            //HEAD2HEAD
            PredictionStatistic(
                title = stringResource(id = R.string.head2head_matches),
                homeTeamPercentText = state.predictions?.comparison_home_h2h ?: "N/A",
                awayTeamPercentText = state.predictions?.comparison_away_h2h ?: "N/A",
                homeTeamPercentNumber = state.predictions?.comparison_home_h2h_number ?: 0,
                awayTeamPercentNumber = state.predictions?.comparison_away_h2h_number ?: 0,
                homeTeamColor = state.homeTeamColor
                    ?: if (isSystemInDarkTheme()) DarkSurfaceInfo else LightSurfaceInfo,
                awayTeamColor = state.awayTeamColor
                    ?: if (isSystemInDarkTheme()) DarkSurfaceInfo else LightSurfaceInfo
            )

            //GOALS HEAD2HEAD
            PredictionStatistic(
                title = stringResource(id = R.string.goals_head2head),
                homeTeamPercentText = state.predictions?.comparison_home_goals_h2h ?: "N/A",
                awayTeamPercentText = state.predictions?.comparison_away_goals_h2h ?: "N/A",
                homeTeamPercentNumber = state.predictions?.comparison_home_goals_h2h_number ?: 0,
                awayTeamPercentNumber = state.predictions?.comparison_away_goals_h2h_number ?: 0,
                homeTeamColor = state.homeTeamColor
                    ?: if (isSystemInDarkTheme()) DarkSurfaceInfo else LightSurfaceInfo,
                awayTeamColor = state.awayTeamColor
                    ?: if (isSystemInDarkTheme()) DarkSurfaceInfo else LightSurfaceInfo
            )
        }
    }
}


@Composable
fun PredictionStatistic(
    title: String,
    homeTeamPercentText: String,
    awayTeamPercentText: String,
    homeTeamPercentNumber: Int,
    awayTeamPercentNumber: Int,
    homeTeamColor: Color,
    awayTeamColor: Color
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {

        val (titleRef, homePercentRef, awayPercentRef, barRef, homeBarRef, awayBarRef) = createRefs()

        //TITLE
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(titleRef) {
                    top.linkTo(parent.top)
                    centerHorizontallyTo(parent)
                }
        )

        //AWAY PERCENT
        Text(
            text = awayTeamPercentText,
            color = if (isSystemInDarkTheme()) DarkOnBackgroundVariant else LightOnBackgroundVariant,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 8.dp, start = 12.dp)
                .constrainAs(awayPercentRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        //HOME PERCENT
        Text(
            text = homeTeamPercentText,
            color = if (isSystemInDarkTheme()) DarkOnBackgroundVariant else LightOnBackgroundVariant,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 8.dp, end = 12.dp)
                .constrainAs(homePercentRef) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )

        //BAR
        Box(
            modifier = Modifier
                .padding(top = 2.dp, start = 12.dp, end = 12.dp)
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(if (isSystemInDarkTheme()) DarkController else LightController)
                .constrainAs(barRef) {
                    top.linkTo(titleRef.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        val verticalGuideline = createGuidelineFromStart(.5f)

        //AWAY BAR
        Box(
            modifier = Modifier
                .padding(start = 24.dp)
                .fillMaxWidth(awayTeamPercentNumber / (2 * 100f))
                .height(6.dp)
                .clip(RoundedCornerShape(topStart = 3.dp, bottomStart = 3.dp))
                .background(awayTeamColor)
                .constrainAs(awayBarRef) {
                    bottom.linkTo(barRef.bottom)
                    end.linkTo(verticalGuideline)
                }
        )

        //HOME BAR
        Box(
            modifier = Modifier
                .padding(end = 24.dp)
                .fillMaxWidth(homeTeamPercentNumber / (2 * 100f))
                .height(6.dp)
                .clip(RoundedCornerShape(topEnd = 3.dp, bottomEnd = 3.dp))
                .background(homeTeamColor)
                .constrainAs(homeBarRef) {
                    bottom.linkTo(barRef.bottom)
                    start.linkTo(verticalGuideline)
                }
        )
    }
}

@Composable
@Preview(name = "Prediction Statistic", showBackground = true)
private fun PreviewPredictionStatistic() {
    ChandChandTheme {
        PredictionStatistic(
            "Form",
            "٪۳۲",
            "٪۴۵",
            80,
            100,
            Color.Red,
            Color.Blue
        )
    }
}

@Composable
@Preview(name = "Prediction Statistics", showBackground = true)
private fun PreviewPredictionStatistics() {
    ChandChandTheme {
        PredictionStatistics(
            PredictionsState(
                homeTeamColor = Color.Red,
                awayTeamColor = Color.Yellow
            )
        )
    }
}