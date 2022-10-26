package com.android.chandchand.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.android.chandchand.presentation.fixtures.navigation.FixturesDestination
import com.android.chandchand.presentation.fixtures.navigation.fixturesGraph
import com.android.chandchand.presentation.fixtures.navigation.liveFixturesGraph
import com.android.chandchand.presentation.fixtures.navigation.somedayFixturesGraph
import com.android.chandchand.presentation.leagues.navigation.leaguesGraph
import com.android.chandchand.presentation.leagues.navigation.standingsGraph
import com.android.chandchand.presentation.predictions.navigation.predictionsGraph
import com.android.chandchand.presentation.ui.navigation.ChandChandNavigationDestination

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