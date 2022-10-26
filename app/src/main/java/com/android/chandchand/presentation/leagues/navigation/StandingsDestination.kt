package com.android.chandchand.presentation.leagues.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.chandchand.presentation.leagues.ui.StandingsRoute
import com.android.chandchand.presentation.ui.navigation.ChandChandNavigationDestination
import kotlinx.coroutines.ExperimentalCoroutinesApi

object StandingsDestination : ChandChandNavigationDestination {
    const val leagueIdArg = "standingsLeagueId"
    const val leagueTitleResIdArg = "standingsLeagueTitleResId"
    override val route: String = "standings_route/{$leagueIdArg}/{$leagueTitleResIdArg}"
    override val destination: String = "standings_destination"

    fun createNavigationRoute(
        leagueId: Int,
        leagueTitleResId: Int,
    ): String {
        return "standings_route/$leagueId/$leagueTitleResId"
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun NavGraphBuilder.standingsGraph(
    onBackClick: () -> Unit
) {
    composable(
        route = StandingsDestination.route,
        arguments = listOf(
            navArgument(StandingsDestination.leagueIdArg) { type = NavType.IntType },
            navArgument(StandingsDestination.leagueTitleResIdArg) { type = NavType.IntType })
    ) { backStackEntry ->
        StandingsRoute(
            onBackClick = onBackClick,
            leagueId = backStackEntry.arguments?.getInt(StandingsDestination.leagueIdArg)!!,
            leagueTitleResId = backStackEntry.arguments?.getInt(StandingsDestination.leagueTitleResIdArg)!!
        )
    }
}