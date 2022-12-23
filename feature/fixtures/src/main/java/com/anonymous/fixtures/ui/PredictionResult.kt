package com.anonymous.fixtures.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.anonymous.designsystem.theme.ChandChandTheme
import com.anonymous.fixtures.PredictionsState
import com.anonymous.fixtures.R

@Composable
fun PredictionResult(state: PredictionsState) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .fillMaxWidth()
            .height(320.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            val (matchResultRef, winnerBadgeRef, winnerLogoRef, winnerNameRef, dividerRef,
                homeTeamLogoRef, awayTeamLogoRef, homeWinningPercentRef, drawPercentRef, awayWinningPercentRef,
                homeWinningChanceRef, drawChanceRef, awayWinningChanceRef) = createRefs()

            //MATCH RESULT
            Text(
                text = stringResource(id = R.string.match_result),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .constrainAs(matchResultRef) {
                        top.linkTo(parent.top)
                        centerHorizontallyTo(parent)
                    }
            )

            //WINNER BADGE
            Image(
                painter = painterResource(id = R.drawable.ic_winner_badge),
                contentDescription = "winner badge",
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(width = 96.dp, height = 120.dp)
                    .constrainAs(winnerBadgeRef) {
                        top.linkTo(matchResultRef.bottom)
                        centerHorizontallyTo(parent)
                    }
            )

            //WINNER LOGO
            if (state.winnerLogoUrl.isNullOrBlank()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_draw_52),
                    contentDescription = "winner logo",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 40.dp)
                        .size(52.dp)
                        .constrainAs(winnerLogoRef) {
                            centerTo(winnerBadgeRef)
                        })
            } else {
                AsyncImage(model = state.winnerLogoUrl,
                    contentDescription = "winner logo",
                    contentScale = ContentScale.Inside,
                    placeholder = painterResource(id = R.drawable.ic_flag_placeholder_32),
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 40.dp)
                        .size(52.dp)
                        .constrainAs(winnerLogoRef) {
                            centerTo(winnerBadgeRef)
                        })
            }

            //WINNER NAME
            Text(
                text = state.winnerTitle,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .constrainAs(winnerNameRef) {
                        top.linkTo(winnerBadgeRef.bottom)
                        centerHorizontallyTo(parent)
                    }
            )

            Divider(
                modifier = Modifier
                    .padding(top = 24.dp, start = 12.dp, end = 12.dp)
                    .constrainAs(dividerRef) {
                        top.linkTo(winnerNameRef.bottom)
                        centerHorizontallyTo(parent)
                    }
            )

            //HOME LOGO
            AsyncImage(model = state.homeTeamLogoUrl,
                contentDescription = "home team logo",
                contentScale = ContentScale.Inside,
                placeholder = painterResource(id = R.drawable.ic_flag_placeholder_32),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(32.dp)
                    .constrainAs(homeTeamLogoRef) {
                        top.linkTo(dividerRef.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    })

            //AWAY LOGO
            AsyncImage(model = state.awayTeamLogoUrl,
                contentDescription = "away team logo",
                contentScale = ContentScale.Inside,
                placeholder = painterResource(id = R.drawable.ic_flag_placeholder_32),
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(32.dp)
                    .constrainAs(awayTeamLogoRef) {
                        top.linkTo(dividerRef.bottom)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    })

            val horizontalChainRef =
                createHorizontalChain(homeWinningPercentRef, drawPercentRef, awayWinningPercentRef)

            constrain(horizontalChainRef) {
                start.linkTo(homeTeamLogoRef.end)
                end.linkTo(awayTeamLogoRef.start)
            }

            val homeVerticalChain = createVerticalChain(
                homeWinningPercentRef,
                homeWinningChanceRef,
                chainStyle = ChainStyle.Packed
            )
            constrain(homeVerticalChain) {
                top.linkTo(dividerRef.bottom)
                bottom.linkTo(parent.bottom)
            }

            val drawVerticalChain = createVerticalChain(
                drawPercentRef, drawChanceRef,
                chainStyle = ChainStyle.Packed
            )
            constrain(drawVerticalChain) {
                top.linkTo(dividerRef.bottom)
                bottom.linkTo(parent.bottom)
            }

            val awayVerticalChain = createVerticalChain(
                awayWinningPercentRef, awayWinningChanceRef,
                chainStyle = ChainStyle.Packed
            )
            constrain(awayVerticalChain) {
                top.linkTo(dividerRef.bottom)
                bottom.linkTo(parent.bottom)
            }

            //HOME PERCENT
            Text(
                text = state.predictions?.winning_percent_home ?: "?%",
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(homeWinningPercentRef) {}
            )

            //HOME CHANCE
            Text(
                text = stringResource(id = R.string.winning_chance),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(homeWinningChanceRef) {
                    start.linkTo(homeWinningPercentRef.start)
                    end.linkTo(homeWinningPercentRef.end)
                }
            )

            //DRAW PERCENT
            Text(
                text = state.predictions?.draws_percent ?: "?%",
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(drawPercentRef) {}
            )

            //DRAW CHANCE
            Text(
                text = stringResource(id = R.string.draw_chance),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(drawChanceRef) {
                    start.linkTo(drawPercentRef.start)
                    end.linkTo(drawPercentRef.end)
                }
            )

            //AWAY PERCENT
            Text(
                text = state.predictions?.winning_percent_away ?: "?%",
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(awayWinningPercentRef) {}
            )

            //AWAY CHANCE
            Text(
                text = stringResource(id = R.string.winning_chance),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(awayWinningChanceRef) {
                    start.linkTo(awayWinningPercentRef.start)
                    end.linkTo(awayWinningPercentRef.end)
                }
            )
        }
    }
}

@Composable
@Preview(name = "Prediction Result", showBackground = true)
private fun PreviewPredictionResult() {
    ChandChandTheme {
        PredictionResult(PredictionsState())
    }
}