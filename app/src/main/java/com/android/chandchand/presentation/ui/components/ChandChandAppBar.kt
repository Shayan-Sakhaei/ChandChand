package com.android.chandchand.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension.Companion.fillToConstraints
import com.android.chandchand.R
import com.android.chandchand.presentation.ui.theme.ChandChandTheme
import com.android.chandchand.presentation.ui.theme.DarkAppBar
import com.android.chandchand.presentation.ui.theme.LightAppBar

@Composable
fun ChandChandAppBar(
    isTopLevelDestination: Boolean = false,
    onBackClick: () -> Unit = {},
    title: String,
    actions: @Composable RowScope.() -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(if (isSystemInDarkTheme()) DarkAppBar else LightAppBar)
    ) {
        val (titleRef, rowRef, backIconRef) = createRefs()
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            content = actions,
            modifier = Modifier.constrainAs(rowRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(titleRef.end, 16.dp)
                width = fillToConstraints
            }
        )

        AnimatedVisibility(
            visible = !isTopLevelDestination,
            modifier = Modifier.constrainAs(backIconRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }) {
            IconButton(onClick = onBackClick) {
                Image(
                    painter = painterResource(
                        id = if (LocalLayoutDirection.current == LayoutDirection.Rtl) R.drawable.ic_arrow_right_24 else R.drawable.ic_arrow_left_24
                    ),
                    contentDescription = "arrow_right_left",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                )
            }
        }
    }
}


@Composable
@Preview(name = "Fixtures")
private fun PreviewFixturesAppBar() {
    ChandChandTheme {
        ChandChandAppBar(
            isTopLevelDestination = false,
            onBackClick = {},
            title = stringResource(R.string.fixtures)
        ) {
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
        ChandChandAppBar(
            isTopLevelDestination = false,
            {},
            title = stringResource(R.string.leagues)
        )
    }
}