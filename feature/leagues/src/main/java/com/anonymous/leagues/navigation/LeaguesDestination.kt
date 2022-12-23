package com.anonymous.leagues.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.anonymous.leagues.ui.LeaguesRoute
import com.anonymous.ui.navigation.ChandChandNavigationDestination

object LeaguesDestination : ChandChandNavigationDestination {
    override val route: String = "leagues_route"
    override val destination: String = "leagues_destination"
}

fun NavGraphBuilder.leaguesGraph(
    onNavigate: (ChandChandNavigationDestination, String) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = LeaguesDestination.route,
        startDestination = LeaguesDestination.destination
    ) {
        composable(route = LeaguesDestination.destination) {
            LeaguesRoute(onNavigate = onNavigate)
        }
        nestedGraphs()
    }
}