package com.android.chandchand.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.android.domain.entities.StandingEntity
import com.android.chandchand.presentation.theme.ChandChandTheme
import com.android.chandchand.presentation.ui.PreviewData

@Composable
fun Standing(standing: com.android.domain.entities.StandingEntity) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
    ) {

        val (point, diff, lose, draw, win, match, name, logo, rowNumber, divider) = createRefs()

        //Point
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .width(32.dp)
                .constrainAs(point) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
            text = "${standing.points}",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primaryVariant,
            textAlign = TextAlign.Center
        )

        //Diff
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .width(38.dp)
                .constrainAs(diff) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(point.end)
                },
            text = "${standing.goalsDiff}",
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center
        )

        //Lose
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .width(32.dp)
                .constrainAs(lose) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(diff.end)
                },
            text = "${standing.allLose}",
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center
        )

        //Draw
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .width(40.dp)
                .constrainAs(draw) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(lose.end)
                },
            text = "${standing.allDraw}",
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center
        )

        //Win
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .width(26.dp)
                .constrainAs(win) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(draw.end)
                },
            text = "${standing.allWin}",
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center
        )

        //Match
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .width(32.dp)
                .constrainAs(match) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(win.end)
                },
            text = "${standing.allMatchsPlayed}",
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center
        )

        //Name
        Text(
            modifier = Modifier
                .padding(start = 4.dp, end = 14.dp)
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(match.end)
                    end.linkTo(logo.start)
                    width = Dimension.fillToConstraints
                },
            text = standing.teamName ?: "",
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center
        )

        //Logo
        AsyncImage(
            model = standing.logo,
            contentDescription = "logo",
            contentScale = ContentScale.Inside,
            placeholder = painterResource(id = R.drawable.ic_flag_placeholder_32),
            modifier = Modifier
                .padding(end = 6.dp)
                .size(32.dp)
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(rowNumber.start)
                }
        )

        //Row Number
        Text(
            modifier = Modifier
                .padding(end = 8.dp)
                .constrainAs(rowNumber) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            text = "${standing.rank}",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center
        )

        Divider(
            modifier = Modifier
                .constrainAs(divider) {
                    start.linkTo(parent.start)
                    end.linkTo(name.end)
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