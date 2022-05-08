package com.android.chandchand.presentation.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.chandchand.R
import com.android.chandchand.presentation.theme.ChandChandTheme

@Composable
fun LeagueTitle(@StringRes titleResId: Int, @DrawableRes logoResId: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
            .height(64.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.padding(start = 16.dp),
                painter = painterResource(id = R.drawable.ic_arrow_left_24),
                contentDescription = "arrow left"
            )

            Text(
                text = stringResource(id = titleResId), modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .weight(1f),
                textAlign = TextAlign.End
            )

            Image(
                modifier = Modifier.padding(end = 12.dp),
                painter = painterResource(id = logoResId),
                contentDescription = "${stringResource(id = titleResId)} logo"
            )
        }
    }
}


@Composable
@Preview
private fun PreviewLeagueTitle() {
    ChandChandTheme {
        LeagueTitle(
            titleResId = R.string.persian_gulf_cup,
            logoResId = R.drawable.ic_persian_gulf_cup_32
        )
    }
}