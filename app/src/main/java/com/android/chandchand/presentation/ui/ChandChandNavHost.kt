package com.android.chandchand.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.android.chandchand.presentation.ui.navigation.*

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
                somedayFixturesGraph(onNavigate = onNavigate, onBackClick = onBackClick)
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