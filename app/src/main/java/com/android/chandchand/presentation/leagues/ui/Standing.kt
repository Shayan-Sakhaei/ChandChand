package com.android.chandchand.presentation.leagues.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.android.chandchand.R
import com.android.chandchand.presentation.ui.PreviewData
import com.android.chandchand.presentation.ui.theme.ChandChandTheme

@Composable
fun Standing(standing: com.android.domain.entities.StandingEntity) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
    ) {

        val (point, diff, lose, draw, win, match, name, logo, rowNumber, divider) = createRefs()

        //Row Number
        Text(
            modifier = Modifier
                .padding(start = 8.dp)
                .constrainAs(rowNumber) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
            text = "${standing.rank}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        //Logo
        AsyncImage(
            model = standing.logo,
            contentDescription = "logo",
            contentScale = ContentScale.Inside,
            placeholder = painterResource(id = R.drawable.ic_flag_placeholder_32),
            modifier = Modifier
                .padding(start = 6.dp)
                .size(32.dp)
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(rowNumber.end)
                }
        )
        //Name
        Text(
            modifier = Modifier
                .padding(start = 12.dp, end = 4.dp)
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(logo.end)
                    end.linkTo(match.start)
                    width = Dimension.fillToConstraints
                },
            text = standing.teamName ?: "",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
        //Match
        Text(
            modifier = Modifier
                .padding(end = 4.dp)
                .width(32.dp)
                .constrainAs(match) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(win.start)
                },
            text = "${standing.allMatchsPlayed}",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
        //Win
        Text(
            modifier = Modifier
                .padding(end = 4.dp)
                .width(26.dp)
                .constrainAs(win) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(draw.start)
                },
            text = "${standing.allWin}",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
        //Draw
        Text(
            modifier = Modifier
                .padding(end = 4.dp)
                .width(40.dp)
                .constrainAs(draw) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(lose.start)
                },
            text = "${standing.allDraw}",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
        //Lose
        Text(
            modifier = Modifier
                .padding(end = 4.dp)
                .width(32.dp)
                .constrainAs(lose) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(diff.start)
                },
            text = "${standing.allLose}",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
        //Diff
        Text(
            modifier = Modifier
                .padding(end = 4.dp)
                .width(38.dp)
                .constrainAs(diff) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(point.start)
                },
            text = "${standing.goalsDiff}",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
        //Point
        Text(
            modifier = Modifier
                .padding(end = 4.dp)
                .width(32.dp)
                .constrainAs(point) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            text = "${standing.points}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
        //Vertical Divider
        Divider(
            modifier = Modifier
                .constrainAs(divider) {
                    start.linkTo(parent.end)
                    end.linkTo(name.start)
                    top.linkTo(parent.bottom)
                },
            color = Color.Black.copy(alpha = .10f),
            thickness = 1.dp
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewStanding() {
    ChandChandTheme {
        Standing(PreviewData.standing)
    }
}