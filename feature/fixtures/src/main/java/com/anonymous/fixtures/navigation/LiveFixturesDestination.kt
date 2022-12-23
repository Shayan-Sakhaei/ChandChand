package com.anonymous.fixtures.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anonymous.fixtures.ui.LiveFixturesRoute
import com.anonymous.ui.navigation.ChandChandNavigationDestination
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