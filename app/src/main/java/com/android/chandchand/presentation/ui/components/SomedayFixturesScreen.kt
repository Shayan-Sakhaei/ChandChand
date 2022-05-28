package com.android.chandchand.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.android.chandchand.R
import com.android.chandchand.presentation.fixtures.FixturesViewModel
import com.android.chandchand.presentation.utils.DAY
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun SomedayFixturesScreen(
    viewModel: FixturesViewModel,
    onCalendarClick: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    Column {
        ChandChandAppBar(title = stringResource(R.string.fixtures)) {
            IconButton(onClick = { onCalendarClick() }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_calendar_badge_24),
                    contentDescription = "calendar"
                )
            }
        }

        ChandChandAppBar(state.somedayDateDescription)

        FixturesPerDay(fixtures = state.somedayFixtures, onHeaderClick = {
            viewModel.onLeagueHeaderClick(it, DAY.SOMEDAY)
        })
    }
}