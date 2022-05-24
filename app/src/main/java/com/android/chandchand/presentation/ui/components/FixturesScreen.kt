package com.android.chandchand.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDirections
import com.android.chandchand.R
import com.android.chandchand.presentation.fixtures.FixturesFragmentDirections
import com.android.chandchand.presentation.fixtures.FixturesViewModel
import com.android.chandchand.presentation.theme.ChandChandTheme
import com.android.chandchand.presentation.utils.DAY
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@Composable
fun FixturesScreen(
    viewModel: FixturesViewModel,
    onNavigate: (NavDirections) -> Unit,
    onCalendarClick: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    val tabs = listOf(
        stringResource(id = R.string.yesterday),
        stringResource(id = R.string.today),
        stringResource(id = R.string.tomorrow),
        stringResource(id = R.string.day_after_tomorrow)
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        ChandChandAppBar(titleResId = R.string.fixtures) {
            IconButton(onClick = { onCalendarClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_24),
                    contentDescription = "calendar",
                    tint = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
                )
            }

            IconButton(onClick = { onNavigate(FixturesFragmentDirections.actionFixturesFragmentToLiveFixturesFragment()) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_tv_24),
                    contentDescription = "calendar",
                    tint = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
                )
            }
        }
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colors.primary,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(4.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(contentColorFor(backgroundColor = MaterialTheme.colors.primary))
                )
            }
        ) {
            tabs.forEachIndexed { index, text ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(text = text) })
            }
        }

        HorizontalPager(
            count = tabs.size,
            modifier = Modifier.weight(1f),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) {
            when (currentPage) {
                0 -> {
                    FixturesPerDay(fixtures = state.yesterdayFixtures, onHeaderClick = {
                        viewModel.onLeagueHeaderClick(it, DAY.YESTERDAY)
                    })
                }
                1 -> {
                    FixturesPerDay(fixtures = state.todayFixtures, onHeaderClick = {
                        viewModel.onLeagueHeaderClick(it, DAY.TODAY)
                    })
                }
                2 -> {
                    FixturesPerDay(fixtures = state.tomorrowFixtures, onHeaderClick = {
                        viewModel.onLeagueHeaderClick(it, DAY.TOMORROW)
                    })
                }
                3 -> {
                    FixturesPerDay(fixtures = state.dayAfterTomorrowFixtures, onHeaderClick = {
                        viewModel.onLeagueHeaderClick(it, DAY.DAY_AFTER_TOMORROW)
                    })
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewFixturesScreen() {
    ChandChandTheme {}
}