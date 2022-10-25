package com.android.chandchand.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.android.chandchand.presentation.fixtures.compose.FixturesRoute
import kotlinx.coroutines.ExperimentalCoroutinesApi

object FixturesDestination : ChandChandNavigationDestination {
    override val route: String = "fixtures_route"
    override val destination: String = "fixtures_destination"
}

@OptIn(ExperimentalCoroutinesApi::class)
fun NavGraphBuilder.fixturesGraph(
    onNavigate: (ChandChandNavigationDestination, String) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = FixturesDestination.route,
        startDestination = FixturesDestination.destination
    ) {
        composable(route = FixturesDestination.destination) {
            FixturesRoute(onNavigate = onNavigate, onCalendarClick = {})
        }
        nestedGraphs()
    }
}