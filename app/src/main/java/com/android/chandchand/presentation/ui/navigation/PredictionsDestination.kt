package com.android.chandchand.presentation.ui.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.chandchand.presentation.predictions.compose.PredictionsRoute

object PredictionsDestination : ChandChandNavigationDestination {
    const val idArg = "predictionsId"
    const val homeTeamLogoArg = "predictionsHomeTeamLogo"
    const val awayTeamLogoArg = "predictionsAwayTeamLogo"
    const val dateArg = "predictionsDateArg"
    const val timeArg = "predictionsTimeArg"
    override val route: String =
        "predictions_route/{${idArg}}/{${homeTeamLogoArg}}/{${awayTeamLogoArg}}/{${dateArg}}/{${timeArg}}"
    override val destination: String = "predictions_destination"

    fun createNavigationRoute(
        id: Int,
        homeTeamLogo: String,
        awayTeamLogo: String,
        date: String,
        time: String
    ): String {
        val encodedHomeTeamLogo = Uri.encode(homeTeamLogo)
        val encodedAwayTeamLogo = Uri.encode(awayTeamLogo)
        val encodedDate = Uri.encode(date)
        val encodedTime = Uri.encode(time)
        return "predictions_route/$id/$encodedHomeTeamLogo/$encodedAwayTeamLogo/$encodedDate/$encodedTime"
    }
}

fun NavGraphBuilder.predictionsGraph(
    onBackClick: () -> Unit
) {
    composable(
        route = PredictionsDestination.route,
        arguments = listOf(
            navArgument(PredictionsDestination.idArg) {
                type = NavType.IntType
            },
            navArgument(PredictionsDestination.homeTeamLogoArg) {
                type = NavType.StringType
            },
            navArgument(PredictionsDestination.awayTeamLogoArg) {
                type = NavType.StringType
            },
            navArgument(PredictionsDestination.dateArg) {
                type = NavType.StringType
            },
            navArgument(PredictionsDestination.timeArg) {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        PredictionsRoute(
            onBackClick = onBackClick,
            id = navBackStackEntry.arguments?.getInt(PredictionsDestination.idArg)!!,
            homeTeamLogoUrl = Uri.decode(
                navBackStackEntry.arguments?.getString(PredictionsDestination.homeTeamLogoArg)!!
            ),
            awayTeamLogoUrl = Uri.decode(
                navBackStackEntry.arguments?.getString(PredictionsDestination.awayTeamLogoArg)!!
            ),
            date = Uri.decode(
                navBackStackEntry.arguments?.getString(PredictionsDestination.dateArg)!!
            ),
            time = Uri.decode(
                navBackStackEntry.arguments?.getString(PredictionsDestination.timeArg)!!
            )
        )
    }
}