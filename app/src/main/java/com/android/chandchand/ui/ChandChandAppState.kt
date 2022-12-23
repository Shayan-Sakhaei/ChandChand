package com.android.chandchand.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.chandchand.R
import com.android.chandchand.navigation.TopLevelDestination
import com.anonymous.fixtures.navigation.FixturesDestination
import com.anonymous.leagues.navigation.LeaguesDestination
import com.anonymous.ui.navigation.ChandChandNavigationDestination

@Composable
fun rememberChandChandAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController()
): ChandChandAppState {
    return remember(navController, windowSizeClass) {
        ChandChandAppState(navController, windowSizeClass)
    }
}

@Stable
class ChandChandAppState(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = listOf(
        TopLevelDestination(
            route = FixturesDestination.route,
            destination = FixturesDestination.destination,
            selectedIconId = R.drawable.ic_soccer_field_24,
            unselectedIconId = R.drawable.ic_soccer_field_24,
            iconTextId = R.string.fixtures
        ),
        TopLevelDestination(
            route = LeaguesDestination.route,
            destination = LeaguesDestination.destination,
            selectedIconId = R.drawable.ic_cup_24,
            unselectedIconId = R.drawable.ic_cup_24,
            iconTextId = R.string.leagues
        )
    )

    fun navigate(destination: ChandChandNavigationDestination, route: String? = null) {
        if (destination is TopLevelDestination) {
            navController.navigate(route ?: destination.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        } else {
            navController.navigate(route ?: destination.route)
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}