package com.android.chandchand.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.android.chandchand.presentation.fixtures.live.compose.LiveFixturesRoute
import kotlinx.coroutines.ExperimentalCoroutinesApi

object LiveFixturesDestination : ChandChandNavigationDestination {
    override val route: String = "live_fixtures_route"
    override val destination: String = "live_fixtures_destination"
}

@OptIn(ExperimentalCoroutinesApi::class)
fun NavGraphBuilder.liveFixturesGraph(
    onBackClick: () -> Unit
) {
    composable(route = LiveFixturesDestination.route) {
        LiveFixturesRoute(onBackClick = onBackClick)
    }
}