package com.android.chandchand.presentation.ui.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.chandchand.presentation.ui.components.SomedayFixturesRoute
import kotlinx.coroutines.ExperimentalCoroutinesApi

object SomedayFixturesDestination : ChandChandNavigationDestination {
    const val selectedDateArg = "somedayFixturesDateArg"
    const val selectedDateDescriptionArg = "somedayFixturesDateDescriptionArg"
    override val route: String =
        "someday_fixtures_route/{$selectedDateArg}/{$selectedDateDescriptionArg}"
    override val destination: String = "someday_fixtures_destination"

    fun createNavigationRoute(
        date: String,
        dateDescription: String
    ): String {
        val encodedDate = Uri.encode(date)
        val encodedDateDescription = Uri.encode(dateDescription)
        return "predictions_route/$encodedDate/$encodedDateDescription"
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun NavGraphBuilder.somedayFixturesGraph(
    onNavigate: (ChandChandNavigationDestination, String) -> Unit,
    onBackClick: () -> Unit
) {
    composable(
        route = SomedayFixturesDestination.route,
        arguments = listOf(
            navArgument(SomedayFixturesDestination.selectedDateArg) {
                type = NavType.StringType
            },
            navArgument(SomedayFixturesDestination.selectedDateDescriptionArg) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        SomedayFixturesRoute(
            onNavigate = onNavigate,
            onBackClick = onBackClick,
            onCalendarClick = { },
            selectedDate = Uri.decode(
                navBackStackEntry.arguments?.getString(SomedayFixturesDestination.selectedDateArg)!!
            ),
            selectedDateDescription = Uri.decode(
                navBackStackEntry.arguments?.getString(SomedayFixturesDestination.selectedDateDescriptionArg)!!
            )
        )
    }
}