package com.example.composetest

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Profile : Screen("profile", R.string.profile, Icons.Filled.Person)
    object Home : Screen("home", R.string.home, Icons.Filled.Home)
    object Settings : Screen("settings", R.string.settings, Icons.Filled.Settings)
}