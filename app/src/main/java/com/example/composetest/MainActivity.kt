package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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

@Composable
fun ListScreen(stringLst: ArrayList<String>, navController: NavHostController) {
    
    val message = remember{ mutableStateOf("") }
    val stringList = remember { mutableStateListOf<String>().also {
        it.addAll(stringLst)
    } }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = message.value,
                placeholder = { Text("Enter text") },
                leadingIcon = { Icon(Icons.Filled.Send, contentDescription = "send") },
                shape = MaterialTheme.shapes.medium.copy(CornerSize(100.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Red,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Green
                ),
                onValueChange = { newText -> message.value = newText },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    stringList.add(message.value)
                }
            ) {
                Text(text = "Add To List")
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(stringList) { index, item ->
                    Text(text = item,
                        modifier = Modifier.clickable {
                            navController.navigate("selectedItemScreen/${stringList[index]}")
                            println("print____${stringList[index]}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SelectedItem(item: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = item)
    }
}
