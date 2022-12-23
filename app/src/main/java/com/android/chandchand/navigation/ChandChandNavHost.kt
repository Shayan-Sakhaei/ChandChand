package com.android.chandchand.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.anonymous.fixtures.navigation.*
import com.anonymous.leagues.navigation.leaguesGraph
import com.anonymous.leagues.navigation.standingsGraph
import com.anonymous.ui.navigation.ChandChandNavigationDestination

@Composable
fun ChandChandNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onNavigate: (ChandChandNavigationDestination, String) -> Unit,
    onBackClick: () -> Unit,
    startDestination: String = FixturesDestination.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        fixturesGraph(onNavigate = onNavigate,
            nestedGraphs = {
                somedayFixturesGraph(
                    onNavigate = onNavigate,
                    onBackClick = onBackClick
                )
                liveFixturesGraph(onBackClick = onBackClick)
                predictionsGraph(onBackClick = onBackClick)
            })
        leaguesGraph(
            onNavigate = onNavigate,
            nestedGraphs = {
                standingsGraph(onBackClick = onBackClick)
            }
        )
    }
}