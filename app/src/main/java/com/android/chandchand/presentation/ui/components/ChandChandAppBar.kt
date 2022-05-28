package com.android.chandchand.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.chandchand.R
import com.android.chandchand.presentation.theme.ChandChandTheme

@Composable
fun ChandChandAppBar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(MaterialTheme.colors.primary)
    ) {
        val titleRef = createRef()
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            color = contentColorFor(backgroundColor = MaterialTheme.colors.primary),
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        Row(
            Modifier.fillMaxHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            content = actions
        )
    }
}


@Composable
@Preview(name = "Fixtures")
private fun PreviewFixturesAppBar() {
    ChandChandTheme {
        ChandChandAppBar(title = stringResource(R.string.fixtures)) {
            IconButton(onClick = { }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_calendar_24),
                    contentDescription = "calendar"
                )
            }

            IconButton(onClick = { }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_tv_24),
                    contentDescription = "calendar"
                )
            }
        }
    }
}

@Composable
@Preview(name = "Leagues")
private fun PreviewLeaguesAppBar() {
    ChandChandTheme {
        ChandChandAppBar(title = stringResource(R.string.leagues))
    }
}