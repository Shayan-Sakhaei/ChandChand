package com.android.chandchand.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.android.chandchand.navigation.ChandChandNavHost
import com.android.chandchand.navigation.TopLevelDestination

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class,
    ExperimentalLayoutApi::class
)
@Composable
fun ChandChandApp(
    windowSizeClass: WindowSizeClass,
    appState: ChandChandAppState = rememberChandChandAppState(windowSizeClass)
) {
    Scaffold(modifier = Modifier.semantics {
        testTagsAsResourceId = true
    },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            ChandChandBottomBar(
                destinations = appState.topLevelDestinations,
                onNavigate = appState::navigate,
                currentDestination = appState.currentDestination
            )
        }) { padding ->
        ChandChandNavHost(
            navController = appState.navController,
            onNavigate = appState::navigate,
            onBackClick = appState::onBackClick,
            modifier = Modifier
                .padding(padding)
                .consumedWindowInsets(padding)
        )
    }
}

@Composable
private fun ChandChandBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigate: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    NavigationBar {
        destinations.forEach { destination ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true

            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(destination) },
                icon = {
                    val iconId = if (selected) {
                        destination.selectedIconId
                    } else {
                        destination.unselectedIconId
                    }
                    Icon(painter = painterResource(id = iconId), contentDescription = null)
                },
                label = {
                    Text(
                        text = stringResource(id = destination.iconTextId),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}