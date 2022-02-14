package com.example.composetest.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composetest.Screen
import com.example.composetest.ui.bottomNav.HomeScreen
import com.example.composetest.ui.bottomNav.ProfileScreen
import com.example.composetest.ui.bottomNav.SettingsScreen
import kotlinx.coroutines.launch

private val stringLst = arrayListOf(
    "first",
    "second",
    "third",
    "fourth"
)

val items = listOf(
    Screen.Profile,
    Screen.Home,
    Screen.Settings
)

@Composable
fun ComposableContainer() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar {
                IconButton(onClick = {
                    scope.launch{ scaffoldState.drawerState.open()}
                }) {
                    Icon(Icons.Filled.Menu, "Side menu")
                }
                Text(text = "Compose is cool")
            }
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            Text("Пункт меню 1", fontSize = 28.sp)
            Text("Пункт меню 2", fontSize = 28.sp)
            Text("Пункт меню 3", fontSize = 28.sp)
        },
        bottomBar = {
            BottomAppBar {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(stringResource(screen.resourceId)) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "listScreen") {
            composable("listScreen") { ListScreen(stringLst, navController) }
            composable(
                "selectedItemScreen/{selectedItem}",
                arguments = listOf(navArgument("selectedItem") { type = NavType.StringType })
            )
            {
                SelectedItem(it.arguments?.getString("selectedItem") ?: "def value")
            }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
        }
    }

}
