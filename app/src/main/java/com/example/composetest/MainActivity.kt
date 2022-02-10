package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composetest.ui.SelectedItem
import com.example.composetest.ui.ListScreen

class MainActivity : ComponentActivity() {

    private val stringLst = arrayListOf("first",
                                "second",
                                "third",
                                "fourth")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "listScreen") {
               composable("listScreen") { ListScreen(stringLst, navController) }
               composable(
                   "selectedItemScreen/{selectedItem}",
                   arguments = listOf(navArgument("selectedItem") { type = NavType.StringType }))
               {
                   SelectedItem(it.arguments?.getString("selectedItem") ?: "def value")
               }
            }
        }
    }
}


