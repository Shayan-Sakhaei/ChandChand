package com.anonymous.fixtures.ui

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.anonymous.designsystem.component.AsyncImageWithDrawable
import com.anonymous.designsystem.theme.ChandChandTheme
import com.anonymous.fixtures.PredictionsState
import com.anonymous.fixtures.R

@Composable
fun PredictionHeader(
    state: PredictionsState,
    onHomeDrawableLoad: (Drawable?) -> Unit,
    onAwayDrawableLoad: (Drawable?) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .height(72.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            val (homeTeamNameRef, homeTeamLogoRef, dateRef,
                timeRef, awayTeamLogoRef, awayTeamNameRef) = createRefs()

            createVerticalChain(dateRef, timeRef)

            Text(
                text = state.date ?: "?",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .constrainAs(dateRef) {
                        centerHorizontallyTo(parent)
                    }
            )

            Text(
                text = state.time ?: "?",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .constrainAs(timeRef) {
                        centerHorizontallyTo(parent)
                    }
            )

            AsyncImageWithDrawable(model = state.homeTeamLogoUrl,
                contentDescription = "home team logo",
                contentScale = ContentScale.Inside,
                placeholderResId = R.drawable.ic_flag_placeholder_32,
                modifier = Modifier
                    .padding(end = 6.dp)
                    .size(40.dp)
                    .constrainAs(homeTeamLogoRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(dateRef.start)
                    }) { drawable -> onHomeDrawableLoad(drawable) }

            Text(
                text = state.predictions?.home_team_name ?: "?",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .constrainAs(homeTeamNameRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(homeTeamLogoRef.start)
                    }
            )

            AsyncImageWithDrawable(model = state.awayTeamLogoUrl,
                contentDescription = "away team logo",
                contentScale = ContentScale.Inside,
                placeholderResId = R.drawable.ic_flag_placeholder_32,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .size(40.dp)
                    .constrainAs(awayTeamLogoRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(dateRef.end)
                    }) { drawable -> onAwayDrawableLoad(drawable) }

            Text(
                text = state.predictions?.away_team_name ?: "?",
                style = MaterialTheme.typography.bodyMedium,
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

@Composable
@Preview(name = "Prediction Header", showBackground = true)
private fun PreviewPredictionHeader() {
    ChandChandTheme {
        PredictionHeader(PredictionsState(), {}, {})
    }
}